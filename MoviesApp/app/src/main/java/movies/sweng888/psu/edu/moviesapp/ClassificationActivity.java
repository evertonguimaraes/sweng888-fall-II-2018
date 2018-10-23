package movies.sweng888.psu.edu.moviesapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import movies.sweng888.psu.edu.moviesapp.R;

public class ClassificationActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    private TextView mTextViewClassification = null;
    private CheckedTextView mCheckedTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);

        mTextViewClassification = (TextView) findViewById(R.id.textViewClassification);
        mCheckedTextView = (CheckedTextView) findViewById(R.id.checkedTextView);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("first_name_str");
        String lastName = intent.getStringExtra("last_name_str");

        String message = mTextViewClassification.getText().toString();

        StringBuilder stringBuilder = new StringBuilder(50);
        stringBuilder.append(message);
        stringBuilder.append(" "+firstName);
        stringBuilder.append(" "+lastName);

        mTextViewClassification.setText(stringBuilder.toString());

        mTextViewClassification.setOnClickListener(this);
        mTextViewClassification.setOnLongClickListener(this);
        mCheckedTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int viewID = v.getId();

        switch (viewID){
            case R.id.checkedTextView: Toast.makeText(this, "doSomething", Toast.LENGTH_SHORT).show();
            case R.id.textViewClassification: Toast.makeText(this, "doSomethingElse", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
