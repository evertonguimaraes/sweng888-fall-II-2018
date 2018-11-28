package mc.sweng888.psu.edu.uiandmaps.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mc.sweng888.psu.edu.uiandmaps.R;
import mc.sweng888.psu.edu.uiandmaps.model.LocationData;
import mc.sweng888.psu.edu.uiandmaps.utils.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditTextUser;
    private EditText mEditTextPassword;

    private Button mButtonSignIn;
    private Button mButtonSignUp;

    // Firebase
    private FirebaseAuth firebaseAuth = null;

    private static final String TAG_AUTH = "AUTH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextUser = (EditText) findViewById(R.id.editTextUser);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);

        mButtonSignIn = (Button) findViewById(R.id.button_login);
        mButtonSignIn.setOnClickListener(this);

        mButtonSignUp = (Button) findViewById(R.id.button_signup);
        mButtonSignUp.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The entry point of the Firebase Authentication SDK
        // Obtain an instance of this class by calling the getInstance() method.
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {

        String user = mEditTextUser.getText().toString();
        String pass = mEditTextPassword.getText().toString();

        // TODO validate input

        switch (v.getId()){
            case R.id.button_login: signIn(user, pass); break;
            case R.id.button_signup: signUp(user, pass); break;
        }
    }

    private void signIn(String user, String pass){
        // TODO Code for sign in
        firebaseAuth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // User successfully authenticated
                        // It creates an async task, and gather the user info.
                        if (task.isSuccessful()){
                            Log.d(TAG_AUTH, getString(R.string.fb_sign_in_success));
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            Utils.createToast(getString(R.string.fb_sign_in_success), getApplicationContext());
                            Intent intent = new Intent(MainActivity.this, LoggedActivity.class);
                            intent.putExtra("CURRENT_USER", firebaseUser);
                            startActivity(intent);

                        }else{
                            Log.d(TAG_AUTH, getString(R.string.fb_sign_in_failed));
                            Utils.createToast(getString(R.string.fb_sign_in_failed), getApplicationContext());
                        }
                    }
                });

    }

    private void signUp(String user, String pass){
        // TODO code for sign up
        firebaseAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            Utils.createToast(getString(R.string.fb_create_success), getApplicationContext());
                        }else{
                            Log.d(TAG_AUTH, getString(R.string.fb_create_failed));
                            Utils.createToast(getString(R.string.fb_create_failed), getApplicationContext());
                        }
                    }
                });
    }
}
