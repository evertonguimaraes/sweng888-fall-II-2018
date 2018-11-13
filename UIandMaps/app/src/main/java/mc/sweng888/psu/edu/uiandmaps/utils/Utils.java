package mc.sweng888.psu.edu.uiandmaps.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static void createToast(String message, Context context){
        Toast.makeText(context,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
