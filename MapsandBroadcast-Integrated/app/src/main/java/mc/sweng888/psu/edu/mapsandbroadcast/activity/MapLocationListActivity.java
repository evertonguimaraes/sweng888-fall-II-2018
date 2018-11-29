package mc.sweng888.psu.edu.mapsandbroadcast.activity;

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
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import mc.sweng888.psu.edu.mapsandbroadcast.R;
import mc.sweng888.psu.edu.mapsandbroadcast.model.MapLocation;
import mc.sweng888.psu.edu.mapsandbroadcast.recyclerview.RecyclerOnItemClickListener;
import mc.sweng888.psu.edu.mapsandbroadcast.recyclerview.RecyclerVIewItemClickListener;
import mc.sweng888.psu.edu.mapsandbroadcast.recyclerview.RecyclerViewAdapter;

public class MapLocationListActivity extends AppCompatActivity {

    public static final String FIREBASE_USER = "FIREBASE_USER";
    public static final String TAG_SELECTED_ITEM = "SELECTED_ITEM";
    public static final String TAG_RECYCLER = "RECYCLER_VIEW";

    // Creating RecyclerView and Adapter as atributte for the class.
    private RecyclerView recyclerView = null;
    private RecyclerViewAdapter recyclerViewAdapter = null;

    //Firebase
    private FirebaseDatabase firebaseDatabase = null;
    private DatabaseReference databaseReference = null;

    // DataSet
    private ArrayList<MapLocation> mapLocations = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location_list);

        // Recovers the current user logged into the firebase.
        // We are not using the user info for now.
        Intent intent = getIntent();
        FirebaseUser firebaseUser = intent.getParcelableExtra(LoginActivity.FIREBASE_USER);
        Log.i(FIREBASE_USER, firebaseUser.getEmail());

        getFirebaseData();

        buildRecyclerView();
    }

    /** Updating the RecyclerView based on the Firebase data.
        Step 1: make RecyclerViewAdapter and RecyclerView as global variables.
        Step 2: call the RecyclerViewAdapter.notifyDataSetChanged **/
    private void getFirebaseData(){

        mapLocations = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                // Get the current location (based on the selected dataset available on firebase
                MapLocation currentLocation = dataSnapshot.getValue(MapLocation.class);

                mapLocations.add(currentLocation); // Adding a new element from the collection


                /** Once the task runs assynchronously, we need to notify the adapter of
                 any changes in the dataset, so it will automatically update the UI. **/
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

    /** Building the RecyclerView and RecyclerViewAdapter based on the dataset. **/
    private void buildRecyclerView(){
        Log.i(TAG_RECYCLER, "buildRecyclerView:called" );
        this.recyclerView = findViewById(R.id.recyclerView);
        this.recyclerViewAdapter = new RecyclerViewAdapter(this, mapLocations);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        this.recyclerView.setAdapter(recyclerViewAdapter);

        // Creating a new listener for the RecyclerView
        recyclerView.addOnItemTouchListener(
                new RecyclerVIewItemClickListener(getApplicationContext(), recyclerView, new RecyclerOnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        int itemPosition = recyclerView.getChildLayoutPosition(view);
                        MapLocation item = mapLocations.get(itemPosition);
                        Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_LONG).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {

                        // TODO Create the intent for the broadcast.
                        Intent intent = new Intent(MapLocationListActivity.this, MapActivity.class);
                        int itemPosition = recyclerView.getChildLayoutPosition(view);
                        MapLocation item = mapLocations.get(itemPosition);
                        intent.putExtra(TAG_SELECTED_ITEM, item);
                        startActivity(intent);
                    }
                })
        );
    }
}
