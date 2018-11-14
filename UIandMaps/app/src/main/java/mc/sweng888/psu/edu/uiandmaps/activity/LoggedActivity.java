package mc.sweng888.psu.edu.uiandmaps.activity;

import android.content.Intent;
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

    // Firebase
    private FirebaseDatabase firebaseDatabase = null;
    private DatabaseReference databaseReference = null;
    ArrayList<LocationData> locationDataList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        Intent intent = getIntent();
        // Recovers the current user logged into the firebase.
        FirebaseUser firebaseUser = intent.getParcelableExtra("CURRENT_USER");
        Log.i(TAG_PARAMS, firebaseUser.getEmail());

        // BEFORE: predefined set of locations
        getFirebaseData();
        buildRecyclerView();

    }

    // AFTER: Get data from firebase.
    // See implementation on method (getFirebaseData)

    private void getFirebaseData(){

        locationDataList = new ArrayList<>();
        //locationDataList = loadDataset();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                LocationData location = dataSnapshot.getValue(LocationData.class);
                Log.d("TAG", location.getLatitude().toString());
                Log.d("TAG", location.getLongitude().toString());
                Log.d("TAG", location.getLocation());
                locationDataList.add(location);
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

    private void buildRecyclerView(){
        Log.i(TAG_RECYCLER, "buildRecyclerView:called" );
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, locationDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

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
