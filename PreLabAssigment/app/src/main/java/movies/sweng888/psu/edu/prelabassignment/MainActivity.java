package movies.sweng888.psu.edu.prelabassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTxtViewName;
    private TextView mTxtViewPhone;
    private EditText mEditTextName;
    private EditText mEditTextPhone;

    private Button mButtonConfirm;
    private Button mButtonCancel;

    private String name;
    private String phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding process layout.xml and java class
        mTxtViewName = (TextView) findViewById(R.id.textViewFirstName);
        mTxtViewPhone = (TextView) findViewById(R.id.textViewPhone);

        mEditTextName = (EditText) findViewById(R.id.editTextFirstName);
        mEditTextPhone = (EditText) findViewById(R.id.editTextPhone);

        mButtonConfirm = (Button) findViewById(R.id.button_confirm);
        mButtonCancel = (Button) findViewById(R.id.button_cancel);
        mButtonConfirm.setOnClickListener(this);
        mButtonCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_confirm: callExplicitIntent(); break;
            case R.id.button_cancel: cleanAnswer(); break;
        }
    }

    private void callExplicitIntent(){

        String name = mEditTextName.getText().toString();
        String phone = mEditTextPhone.getText().toString();

        if(!name.isEmpty() && !phone.isEmpty()){
            Toast.makeText(getApplicationContext(), "mButtonConfirm: doSomething", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("NAME_STR", name);
            intent.putExtra("PHONE_STR", phone);
            startActivity(intent);
            Log.i("INFO", "passed callExplicitIntent");
        }else{
            if(name.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please, provide the person name", Toast.LENGTH_SHORT).show();
            }else{
                if(phone.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please, provide a phone number", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Please, provide both name and phone number", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void cleanAnswer(){
        Toast.makeText(this, "mButtonCancel: Cleaning", Toast.LENGTH_LONG).show();
        mEditTextName.setText("");
        mEditTextPhone.setText("");
    }
}
