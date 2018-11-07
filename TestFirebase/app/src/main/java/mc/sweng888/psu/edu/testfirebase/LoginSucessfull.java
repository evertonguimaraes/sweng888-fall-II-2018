package mc.sweng888.psu.edu.testfirebase;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class LoginSucessfull extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sucessfull);

        // Recover the data from the other activity passed through the intent.
        UserData userData = (UserData) getIntent().getSerializableExtra("USER_DATA");

        Log.d("PARAMS", userData.getName());
        Log.d("PARAMS", userData.getProvider());
    }
}
