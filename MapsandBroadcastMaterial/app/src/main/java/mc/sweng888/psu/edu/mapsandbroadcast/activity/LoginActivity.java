package mc.sweng888.psu.edu.mapsandbroadcast.activity;

import android.app.Activity;
import android.content.Intent;
import android.service.autofill.UserData;
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

import mc.sweng888.psu.edu.mapsandbroadcast.R;

public class LoginActivity extends Activity implements View.OnClickListener {

    public static final String FIREBASE_USER = "FIREBASE_USER";

    // UI elements
    private Button mButtonSignIn;
    private Button mButtonSignUp;
    private EditText mEditTextUser;
    private EditText mEditTextPassword;

    /** FirebaseAuth
     * The entry point of the Firebase Authentication SDK.
     * https://firebase.google.com/docs/reference/android/com/google/firebase/auth/FirebaseAuth **/
    private FirebaseAuth mAuth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mButtonSignIn = (Button) findViewById(R.id.button_login);
        mButtonSignUp = (Button) findViewById(R.id.button_signup);
        mEditTextUser = (EditText) findViewById(R.id.editTextUser);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);

        mButtonSignIn.setOnClickListener(this);
        mButtonSignUp.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /* Obtain an instance of this class by calling getInstance() */
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        String email = mEditTextUser.getText().toString();
        String password = mEditTextPassword.getText().toString();

        switch (v.getId()){
            case R.id.button_login: signIn(email, password); break;
            case R.id.button_signup: signUp(email, password); break;
        }
    }

    private void signIn(String email, String password){

        // Tries to sign in a user with the given email address and password.
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // If sign is sucessful, update UI with the signed-in user's information
                            Log.d(FIREBASE_USER,String.valueOf(R.string.sign_in_success));
                            // Get the current user from Firebase
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Pass the user info as paramater to the next activity.
                            Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                            intent.putExtra(FIREBASE_USER, user);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", String.valueOf(R.string.sign_in_fail), task.getException());
                            String msgFailed =  R.string.auth_failed + task.getException().getMessage();
                            Toast.makeText(LoginActivity.this, // context
                                    String.valueOf(R.string.auth_failed) + task.getException().getMessage(), // Message to be displayed
                                    Toast.LENGTH_SHORT).show(); // length for the toast
                        }
                    }
                });
    }

    private void signUp(String email, String password){


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // If sign in sucessfull, display a message to the user.
                    Log.d(FIREBASE_USER,String.valueOf(R.string.sign_up_success));
                    // Get user information.
                    FirebaseUser user = mAuth.getCurrentUser();
                    String msg = "User: "+user.getEmail()+" created successfully";
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

                }else{
                    // TODO
                    // If sign in fails, display a message to the user.
                    Log.d(FIREBASE_USER, String.valueOf(R.string.sign_up_fail), task.getException());
                    String message =  R.string.auth_failed + task.getException().getMessage();
                    Toast.makeText(LoginActivity.this, // context
                            message, // Message to be displayed
                            Toast.LENGTH_SHORT).show(); // length for the toast

                }
            }
        });
    }
}
