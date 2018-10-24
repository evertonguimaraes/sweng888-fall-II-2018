package movies.sweng888.psu.edu.prelabassignment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextViewMsg;
    private Button mButtonCall;

    private String name;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTextViewMsg = (TextView) findViewById(R.id.second_text_view_msg);
        mButtonCall = (Button) findViewById(R.id.button_call);
        mButtonCall.setOnClickListener(this);

        String msg = mTextViewMsg.getText().toString();

        Intent intent = getIntent();
        this.name = intent.getStringExtra("NAME_STR");
        this.phone = intent.getStringExtra("PHONE_STR");

        StringBuilder stringBuilder = new StringBuilder(30);
        stringBuilder.append(msg);
        stringBuilder.append(" " + name);

        mTextViewMsg.setText(stringBuilder);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        String uri = "tel:" + phone.trim();
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
}
