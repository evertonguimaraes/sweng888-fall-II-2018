package movies.sweng888.psu.edu.moviesapp.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import movies.sweng888.psu.edu.moviesapp.R;
import movies.sweng888.psu.edu.moviesapp.model.entity.dao.DatabaseAccess;
import movies.sweng888.psu.edu.moviesapp.model.entity.dao.PersistenceUserProfile;
import movies.sweng888.psu.edu.moviesapp.model.entity.entity.UserProfile;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<UserProfile> userProfiles;
    private PersistenceUserProfile persistenceUserProfile;

    private Button mButtonSignUp;
    private Button mButtonLogin;

    private EditText mEditTextUser;
    private EditText mEditTextPassword;

    private ConstraintLayout mConstraintLayoutLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonSignUp = (Button) findViewById(R.id.button_signup);

        mEditTextUser = (EditText) findViewById(R.id.editTextUser);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);

        // The constraint layout will be used for displaying the Snackbar.
        mConstraintLayoutLogin = (ConstraintLayout) findViewById(R.id.constraint_layout_login);

        //
        persistenceUserProfile = new PersistenceUserProfile(this);

        insertData();

        // TODO replace by a simple query to verify if the user exists in the database.
        // TODO customize query to check this info on the UserProfileTable
        userProfiles = persistenceUserProfile.getDataFromDB();

        mButtonLogin.setOnClickListener(this);
        mButtonSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_login: validateInput(); break;
            case R.id.button_signup: signUp(); break;
        }

    }

    private void validateInput() {

        UserProfile userProfile = null;

        if(userProfiles != null && !userProfiles.isEmpty()){

            String user = mEditTextUser.getText().toString();
            String password = mEditTextPassword.getText().toString();

            for (UserProfile up : userProfiles){
                if(up.getUsername().equals(user) ){
                    userProfile = up;
                }
                break;
            }
            if(userProfile == null){
                Toast.makeText(getApplicationContext(), R.string.msg_user_not_found, Toast.LENGTH_LONG).show();
            }else {
                if(!userProfile.getPassword().equals(password)){
                    Toast.makeText(getApplicationContext(), R.string.msg_pass_wrong, Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                    intent.putExtra("USER", userProfile.getName());
                    intent.putExtra("PASSWORD", userProfile.getPassword());
                    startActivity(intent);
                }
            }
        }
    }

    private void signUp(){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void insertData(){

        persistenceUserProfile.insert(new UserProfile("Luke", "Cage",
                "lc00", "222-555-444", "lc00@psu.edu",
                "1234", new Date(20181010)));

        persistenceUserProfile.insert(new UserProfile("Everton", "Guimaraes",
                "jim09", "222-333-444", "jim09@psu.edu",
                "1234", new Date(20181010)));

    }

}
