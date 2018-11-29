package mc.sweng888.psu.edu.mapsandbroadcast.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mc.sweng888.psu.edu.mapsandbroadcast.R;
import mc.sweng888.psu.edu.mapsandbroadcast.broadcast.BroadcastReceiverMap;
import mc.sweng888.psu.edu.mapsandbroadcast.helper.MapLocationHelper;
import mc.sweng888.psu.edu.mapsandbroadcast.model.MapLocation;

public class MapActivity extends Activity implements OnMapReadyCallback{

    private final String LOG_MAP = "GOOGLE_MAPS";

    // Google Maps
    private LatLng currentLatLng;
    private MapFragment mapFragment;
    private Marker currentMapMarker;

    // Firebase
    FirebaseDatabase firebaseDatabase = null;
    DatabaseReference databaseReference = null;

    // Broadcast Receiver
    private IntentFilter intentFilter = null;
    private BroadcastReceiverMap broadcastReceiverMap = null;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);

        // Instantiating a new IntentFilter to support BroadcastReceivers
        intentFilter = new IntentFilter("mc.sweng888.psu.edu.newmapsexample.action.NEW_MAP_LOCATION_BROADCAST");
        broadcastReceiverMap = new BroadcastReceiverMap();

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
    }

    // Step 1 - Set up initial configuration for the map.
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        Intent intent = getIntent();
        MapLocation mapLocation =  (MapLocation) intent.getSerializableExtra(MapLocationListActivity.TAG_SELECTED_ITEM);

        // Set the camera focus on the current LatLtn object, and other map properties.
        mapCameraConfiguration(mapLocation.getLatLng());
        useMapClickListener();
        useMarkerClickListener();
        createMarkersFromFirebase();
    }

    // Step 2
    /** Set a few properties for the map when it is ready to be displayed.
     Zoom position varies from 2 to 21.
     Camera position implements a builder pattern, which allows to customize the view.
     Bearing - screen rotation ( the angulation needs to be defined ).
     Tilt - screen inclination ( the angulation needs to be defined ).
     **/
    private void mapCameraConfiguration(LatLng latLng){

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng) // the location that the camera is pointing at.
                .zoom(4) // the zoom level near the center of the screen.
                .bearing(0) // the direction that the camera is pointing in, in degrees clockwise from north.
                .build();

        // Camera that makes reference to the maps view
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        mMap.addMarker(createCustomMapMarkersFromLocation(latLng));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        mMap.animateCamera(cameraUpdate, 3000, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                Log.i(LOG_MAP, "googleMap.animateCamera:onFinish is active");
            }

            @Override
            public void onCancel() { Log.i(LOG_MAP, "googleMap.animateCamera:onCancel is active"); }});
    }

    // Step 3
    /** This method is called everytime the use wants to place a new marker on the map. **/
    private MarkerOptions createCustomMapMarkersFromLocation(LatLng latLng){

        MarkerOptions markerOptions = new MarkerOptions();
        MapLocation mapLocation = MapLocationHelper.getAddressFromLatLgn(getApplicationContext(), latLng);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        markerOptions.position(latLng) // coordinates
                .title(mapLocation.getCity()) // location name
                .snippet(mapLocation.toString()); // location description

        return markerOptions;
    }

    // Step 4 - Define a new marker based on a Map click (uses onMapClickListener)
    private void useMapClickListener(){

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latltn) {
                Log.i(LOG_MAP, "setOnMapClickListener");

                // Remove current marker from the map.
                if(currentMapMarker != null)  currentMapMarker.remove();

                // The current marker is updated with the new position based on the click.
                MarkerOptions markerOptions = createCustomMapMarkersFromLocation(latltn);
                currentMapMarker = mMap.addMarker(markerOptions);
            }
        });
    }

    // Step 5 - Use OnMarkerClickListener for displaying information about the MapLocation
    private void useMarkerClickListener(){
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            // If FALSE, the map will have the standard behavior (based on the android framework)
            // When the marker is clicked, it will focus on the specific point on the map and show
            // the InfoWindow. IF TRUE, a new behavior needs to be specified in the source code.
            // However, you are not required to change the behavior for this method.
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng latLng = marker.getPosition();
                MapLocation mapLocation = MapLocationHelper.getAddressFromLatLgn(getApplicationContext(), latLng);

                if(mapLocation != null){
                    Log.d("MAP_LOCATION", mapLocation.toString());
                    Toast.makeText(getApplicationContext(), mapLocation.toString(), Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(getApplicationContext(), "Location not found.", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    // Step 6 - Create custom markers
    public void createMarkersFromFirebase(){

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                // Get the current location (based on the selected dataset available on firebase
                MapLocation currentLocation = dataSnapshot.getValue(MapLocation.class);

                // Adding a new element from the collection
                MarkerOptions markerOptions = createCustomMapMarkersFromLocation(currentLocation.getLatLng());
                mMap.addMarker(markerOptions);

                // Create the notifications
                Intent broadcastIntent = new Intent(BroadcastReceiverMap.NEW_MAP_LOCATION_BROADCAST);
                broadcastIntent.putExtra("LATITUDE", currentLocation.getLatitude());
                broadcastIntent.putExtra("LONGITUDE", currentLocation.getLongitude());
                broadcastIntent.putExtra("LOCATION", currentLocation.getLatLng());
                sendBroadcast(broadcastIntent);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Register the Broadcast Receiver.
        registerReceiver(broadcastReceiverMap, intentFilter);
    }

    @Override
    protected void onStop() {
        // Unregister the Broadcast Receiver
        unregisterReceiver(broadcastReceiverMap);
        super.onStop();
    }

}
