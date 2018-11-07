package movies.sweng888.psu.edu.moviesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import movies.sweng888.psu.edu.moviesapp.R;
import movies.sweng888.psu.edu.moviesapp.model.entity.entity.UserProfile;

public class LoginSuccessActivity extends AppCompatActivity {

    private TextView mTextViewUser;
    private TextView mTextViewPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        mTextViewUser = (TextView) findViewById(R.id.textViewUser);
        mTextViewPass = (TextView) findViewById(R.id.textViewPass);

        Intent intent = getIntent();
        String user = intent.getStringExtra("USER");
        String pass = intent.getStringExtra("PASSWORD");

        String msg1 = mTextViewUser.getText().toString();
        StringBuilder stringBuilder = new StringBuilder(30);
        stringBuilder.append(msg1);
        stringBuilder.append(": "+user);

        String msg2 = mTextViewPass.getText().toString();
        StringBuilder stringBuilder2 = new StringBuilder(30);
        stringBuilder.append(msg2);
        stringBuilder.append(": "+pass);

        mTextViewUser.setText(stringBuilder);
        mTextViewPass.setText(stringBuilder2);

   }
}
