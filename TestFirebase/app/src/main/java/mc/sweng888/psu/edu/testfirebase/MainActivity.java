package mc.sweng888.psu.edu.testfirebase;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

public class MainActivity extends Activity implements View.OnClickListener {

    // UI
    private Button mButtonSignIn;
    private Button mButtonSignUp;
    private EditText mEditTextUser;
    private EditText mEditTextPassword;

    // FirebaseAuth
    // The entry point of the Firebase Authentication SDK.
    // You need to obtain an instance of this class by calling getInstance()
    // https://firebase.google.com/docs/reference/android/com/google/firebase/auth/FirebaseAuth
    private FirebaseAuth mAuth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonSignIn = (Button) findViewById(R.id.button_login);
        mButtonSignUp = (Button) findViewById(R.id.button_signup);
        mEditTextUser = (EditText) findViewById(R.id.editTextUser);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);

        mButtonSignIn.setOnClickListener(this);
        mButtonSignUp.setOnClickListener(this);

        /*
         * The entry point of the Firebase Authentication SDK.
         * Obtain an instance of this class by calling getInstance()
         */

    }

    @Override
    protected void onStart() {
        super.onStart();
        /*
         * The entry point of the Firebase Authentication SDK.
         * Obtain an instance of this class by calling getInstance()
         */
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

        mAuth = FirebaseAuth.getInstance();

        // Tries to sign in a user with the given email address and password.
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // If sign is sucessful, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Accessing the user information
                            // Creating a Toast to update the UI
                            String msg = "User: "+user.getEmail()+" , ID: "+user.getProviderId();
                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                            // Pass the user info as paramater to the next activity.
                            Intent intent = new Intent(MainActivity.this, LoginSucessfull.class);
                            intent.putExtra("USER_DATA", new UserData(user.getEmail(), user.getProviderId()));

                        } else {
                            // TODO Implemnt the validation in case password or user is wrong.
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            String msgFailed = "Authentication failed: "+task.getException().getMessage();
                            Toast.makeText(MainActivity.this,msgFailed,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signUp(String email, String password){

        /*
         * The entry point of the Firebase Authentication SDK.
         * Obtain an instance of this class by calling getInstance()
         */
        //mAuth = FirebaseAuth.getInstance();


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // If sign in sucessfull, display a message to the user.
                    Log.d("USER_AUTH", "createUserWithEmailAndPassword.success");
                    // Get user information.
                    FirebaseUser user = mAuth.getCurrentUser();

                    String msg = "Someone: "+user.getEmail()+" , ID: "+user.getProviderId();
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, SignUpSuccess.class);
                    startActivity(intent);

                }else{
                    // TODO
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmailAndPassword:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed "+task.getException().getMessage() ,
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
