package mc.sweng888.psu.edu.mapsandbroadcast.broadcast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import mc.sweng888.psu.edu.mapsandbroadcast.R;
import mc.sweng888.psu.edu.mapsandbroadcast.activity.MainActivity;
import mc.sweng888.psu.edu.mapsandbroadcast.model.MapLocation;

public class BroadcastReceiverMap extends BroadcastReceiver  {

    private static final String MAP_TAG = "MAP_TAG";

    public static final String MAP_BROADCAST = "mc.sweng888.psu.edu.mapsandbroadcast.action.MAP_BROADCAST";

    public static final int CHANNEL_ID = 1;
    public static final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;
    public static final String CHANNEL_DESCRIPTION = "BROADCAST MAP CHANNEL";
    public static final String CHANNEL_NAME = "MAPS";

    private NotificationManager notificationManager;
    private Notification.Builder builder;

    // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("BROADCAST", "onReceive");

        // Gather the information / params from the Intent.
        MapLocation mapLocation =  (MapLocation) intent.getSerializableExtra(MainActivity.LOCATION_PARAMS);

        // It assumes the highest and lowest latitude on Earth as being, respectively, 90 and -90.
        // Any points between -23 and 23 is considered as CENTRAL HEMISPHERE, as it is close
        // to the Equator.
        String hemisphere = getHemisphere(mapLocation.getLatitude());


        if (hemisphere.equals("NORTH") || hemisphere.equals("SOUTH") || hemisphere.equals("SOUTH")){

            // Create an instance of the NotificationManager
            // It call the static function from(Context) to get a NotificationManagerCompat object, and then call
            // one of its methods to post or cancel notifications
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // If you are using the Android API above then 26, you should use another constructor.
            builder = new Notification.Builder(context, CHANNEL_NAME);

            // Set up the notification Title and Text.
            builder.setSmallIcon(R.drawable.broadcast);
            builder.setContentTitle(mapLocation.getCity());
            builder.setContentText(Double.isNaN(mapLocation.getLatitude())
                                    || Double.isNaN(mapLocation.getLongitude())
                    ? "Location Unknown" :
                    "Located at the " + hemisphere +
                            ", with coordinates (lat, lng): "+
                            mapLocation.getLatitude()+", "+mapLocation.getLongitude());

            // Set the notification channel
            notificationManager.createNotificationChannel(getNotificationChannel());

            // Set the notification ID, and passing as parameter the Notification.Builder.
            notificationManager.notify(CHANNEL_ID, builder.build());

        }else
            Log.d(MAP_TAG, String.valueOf(R.string.location_out));

    }

    private NotificationChannel getNotificationChannel(){

        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_NAME, CHANNEL_DESCRIPTION, CHANNEL_IMPORTANCE);
        notificationChannel.setDescription(CHANNEL_DESCRIPTION);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.BLUE);
        notificationChannel.enableVibration(true);
        notificationChannel.setShowBadge(true);

        return notificationChannel;
    }

    private String getHemisphere(Double latitude){

        String hemisphere = "";
        boolean isCentralHemisphere = (latitude < 23 && latitude > -23);
        boolean isNorthHemisphere = (latitude > 23 && latitude <= 90);
        boolean isSouthHemisphere = (latitude < -23 && latitude >= -90);

        if(isCentralHemisphere)
            hemisphere = "CENTRAL";
        else{
            if (isNorthHemisphere) hemisphere = "NORTH";
            else{
                if (isSouthHemisphere)
                    hemisphere = "SOUTH";
            }
        }

        return hemisphere;
    }


}







