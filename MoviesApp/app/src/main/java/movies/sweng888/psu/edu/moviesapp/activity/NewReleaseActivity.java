package movies.sweng888.psu.edu.moviesapp.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import movies.sweng888.psu.edu.moviesapp.R;

public class NewReleaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);

        Log.d("ReleaseActivity", "onCreate");
        Toast.makeText(this, "MainActivity.onLongClick() has been activated", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CategoryActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CategoryActivity", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("CategoryActivity", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CategoryActivity", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CategoryActivity", "onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CategoryActivity", "onDestroy()");
    }
}
