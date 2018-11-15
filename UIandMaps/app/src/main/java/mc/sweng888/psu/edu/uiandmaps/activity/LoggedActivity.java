package mc.sweng888.psu.edu.uiandmaps.activity;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import mc.sweng888.psu.edu.uiandmaps.R;
import mc.sweng888.psu.edu.uiandmaps.adapter.RecyclerViewAdapter;
import mc.sweng888.psu.edu.uiandmaps.model.LocationData;

public class LoggedActivity extends AppCompatActivity {

    private static final String TAG_PARAMS = "PARAMS";
    private static final String TAG_RECYCLER = "RECYCLER_VIEW";

    // Creating RecyclerView and Adapter as atributte for the class.
    private RecyclerView recyclerView = null;
    private RecyclerViewAdapter recyclerViewAdapter = null;

    // DataSet
    private ArrayList<LocationData> locationDataList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        Intent intent = getIntent();
        // Recovers the current user logged into the firebase.
        // We are not using the user info for now.
        FirebaseUser firebaseUser = intent.getParcelableExtra("CURRENT_USER");
        Log.i(TAG_PARAMS, firebaseUser.getEmail());

        // Gathering the data
        // BEFORE: Using an ArrayList with MOCK Objects.
        //locationDataList = loadDataset();

        // AFTER: Using firebase.
        getFirebaseData();

        buildRecyclerView();

    }

    // Updating the RecyclerView based on the Firebase data.
    // Step 1: make RecyclerViewAdapter and RecyclerView as global variables.
    // Step 2: call the RecyclerViewAdapter.notifyDataSetChanged
    private void getFirebaseData(){

        locationDataList = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                LocationData currentLocation = dataSnapshot.getValue(LocationData.class);
                Log.d("TAG", currentLocation.getLatitude().toString());
                Log.d("TAG", currentLocation.getLongitude().toString());
                Log.d("TAG", currentLocation.getLocation());

                // Adding a new element from the
                locationDataList.add(currentLocation);

                // Once the task runs assynchronously, we need to notify the adapter of
                // any changes in the dataset, so it will automatically update the UI.
                recyclerViewAdapter.notifyDataSetChanged();
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

    // Building the RecyclerView and RecyclerViewAdapter based on the dataset.
    private void buildRecyclerView(){
        Log.i(TAG_RECYCLER, "buildRecyclerView:called" );
        this.recyclerView = findViewById(R.id.recyclerView);
        this.recyclerViewAdapter = new RecyclerViewAdapter(this, locationDataList);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        this.recyclerView.setAdapter(recyclerViewAdapter);

        // TODO Adding support to events.

    }

    // Creating Mock objects for testing purposes.
    private ArrayList<LocationData> loadDataset(){

        ArrayList<LocationData> locationDataList = new ArrayList<>();
        locationDataList.add(new LocationData(40.741895, -73.989308, "New York"));
        locationDataList.add(new LocationData(39.9525839, -75.16522150000003, "Philadelphia"));
        locationDataList.add(new LocationData(34.0522342, -118.2436849, "Los Angeles"));
        locationDataList.add(new LocationData(34.0521468, -118.24375700000002, "Washington D.C."));
        locationDataList.add(new LocationData(40.5852602, -105.08442300000002, "Fort Collins"));
        locationDataList.add(new LocationData(25.7616798, -80.19179020000001, "Miami"));
        locationDataList.add(new LocationData(29.7604267, -95.3698028, "Houston"));

        return locationDataList;
    }
}
