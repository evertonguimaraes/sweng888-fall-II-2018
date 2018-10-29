package mc.sweng888.psu.edu.ampyourapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import mc.sweng888.psu.edu.ampyourapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonLogin = null;
    private Button mButtonSignUp = null;

    private EditText mEditTextUser = null;
    private EditText mEditTextPass = null;

    // Those classes will be used for validating the user input and provide some feedback once the
    // login / sign up is completed
    private TextInputLayout mInputLayoutUser;
    private TextInputLayout mInputLayoutPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextUser = findViewById(R.id.editTextUser);
        mEditTextPass = findViewById(R.id.editTextPassword);
        mButtonLogin = findViewById(R.id.button_login);
        mButtonSignUp = findViewById(R.id.button_signup);

        mButtonLogin.setOnClickListener(this);
        mButtonSignUp.setOnClickListener(this);
    }

    // it should also support password retrieval
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_login: login(); break;
            case R.id.button_signup: signUp(); break;
        }
    }

    private void login(){
        String user = mEditTextUser.getText().toString();
        String pass = mEditTextPass.getText().toString();

        // TODO implement firebase login and pass in as parameters user / password.
    }

    private void signUp(){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }


    // TODO Methods for validating input data.

}
