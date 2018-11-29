package mc.sweng888.psu.edu.mapsandbroadcast.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import mc.sweng888.psu.edu.mapsandbroadcast.R;
import mc.sweng888.psu.edu.mapsandbroadcast.broadcast.BroadcastReceiverMap;
import mc.sweng888.psu.edu.mapsandbroadcast.helper.MapLocationHelper;
import mc.sweng888.psu.edu.mapsandbroadcast.model.MapLocation;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final String LOCATION_PARAMS = "LOCATION";

    public IntentFilter intentFilter = null;
    public BroadcastReceiverMap broadcastReceiverMap = null;
    private Button button = null;

    //UI
    private EditText mEditTextLat;
    private EditText mEditTextLong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mEditTextLat = (EditText) findViewById(R.id.edit_text_lat);
        mEditTextLong = (EditText) findViewById(R.id.edit_text_long);

        button = (Button) findViewById(R.id.button_send_intent);
        button.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpBroadcastReceiver(); // Register the Broadcast Receiver.
    }

    @Override
    protected void onStop() {
        // Unregister the Broadcast Receiver
        unregisterReceiver(broadcastReceiverMap);
        super.onStop();
    }

    @Override
    public void onClick(View v) {

        Double lat = Double.valueOf(mEditTextLat.getText().toString());
        Double lng = Double.valueOf(mEditTextLong.getText().toString());
        LatLng latLng = new LatLng(lat, lng);

        MapLocation mapLocation = MapLocationHelper.getAddressFromLatLgn(getApplicationContext(), latLng);

        Intent broadcastIntent = new Intent("mc.sweng888.psu.edu.mapsandbroadcast.action.MAP_BROADCAST");
        broadcastIntent.putExtra(LOCATION_PARAMS, mapLocation);
        sendBroadcast(broadcastIntent);

        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        intent.putExtra(LOCATION_PARAMS, mapLocation);
        startActivity(intent);
    }

    private void setUpBroadcastReceiver(){
        intentFilter = new IntentFilter(BroadcastReceiverMap.MAP_BROADCAST);
        broadcastReceiverMap = new BroadcastReceiverMap();
        registerReceiver(broadcastReceiverMap, intentFilter);
    }
}
