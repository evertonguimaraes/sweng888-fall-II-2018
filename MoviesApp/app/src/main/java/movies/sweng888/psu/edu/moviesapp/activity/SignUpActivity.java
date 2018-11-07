package movies.sweng888.psu.edu.moviesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import movies.sweng888.psu.edu.moviesapp.R;
import movies.sweng888.psu.edu.moviesapp.model.entity.dao.PersistenceUserProfile;
import movies.sweng888.psu.edu.moviesapp.model.entity.entity.UserProfile;

public class SignUpActivity extends AppCompatActivity {

    // SQLite
    private PersistenceUserProfile persistenceUserProfile;

    // UI
    private EditText mEditTextFirst;
    private EditText mEditTextLast;
    private EditText mEditTextUsername;
    private EditText mEditTextBirthday;
    private EditText mEditTextPass;
    private EditText mEditTextEmail;
    private EditText mEditTextPhone;

    private Button mButtonCreateNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEditTextFirst = (EditText) findViewById(R.id.editTextName);
        mEditTextLast = (EditText) findViewById(R.id.editTextLastName);
        mEditTextUsername = (EditText) findViewById(R.id.editTextUsername);
        mEditTextBirthday = (EditText) findViewById(R.id.editTextBirtday);
        mEditTextPass = (EditText) findViewById(R.id.editTextPass);
        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextPhone = (EditText) findViewById(R.id.editTextPhone);

        mButtonCreateNewUser = (Button) findViewById(R.id.button_new_user);

        mButtonCreateNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String first = mEditTextFirst.getText().toString();
                String last = mEditTextLast.getText().toString();
                String username = mEditTextUsername.getText().toString();
                String pass = mEditTextPass.getText().toString();
                String email = mEditTextEmail.getText().toString();
                String phone = mEditTextPhone.getText().toString();
                String birthday = mEditTextBirthday.getText().toString();

//                String pattern = "yyyy-MM-dd";
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//                String date = simpleDateFormat.format(new Date());

                UserProfile user = new UserProfile(first, last,
                        username, phone, email, pass, birthday);

                persistenceUserProfile.insert(user);

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "mButtonCreateNewUser.success", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        persistenceUserProfile = new PersistenceUserProfile(this);
    }

    private void insertData(){

        persistenceUserProfile.insert(new UserProfile("Jack", "Mah",
                "jack90", "222-555-444", "jack90@psu.edu",
                "1234", "10-10-2018"));

        persistenceUserProfile.insert(new UserProfile("Everton", "Guimaraes",
                "jim09", "222-333-444", "jim09@psu.edu",
                "1234", "10-10-2018"));

    }
}
