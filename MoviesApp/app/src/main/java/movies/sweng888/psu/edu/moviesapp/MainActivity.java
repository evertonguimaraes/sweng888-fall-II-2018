package movies.sweng888.psu.edu.moviesapp;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button mButtonContact = null;
    private ImageView mImageViewProfile = null;

    private TextView mTextViewCategory = null;
    private TextView mTextViewRelease = null;
    private TextView mTextViewRating = null;
    private TextView mTextViewClassification = null;

    private ConstraintLayout mConstraintLayout = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding layout.xml elements and java source code.
        mTextViewCategory = (TextView) findViewById(R.id.movie_category);
        mTextViewRelease = (TextView) findViewById(R.id.movie_releases);
        mTextViewRating = (TextView) findViewById(R.id.movie_rating);
        mTextViewClassification = (TextView) findViewById(R.id.movie_classification);

        mButtonContact = (Button) findViewById(R.id.button_contact);

        mImageViewProfile = (ImageView) findViewById(R.id.image_view_profile);

        mConstraintLayout = (ConstraintLayout) findViewById(R.id.constraint_layout);

        // Set onClickListener to the textView

        mTextViewCategory.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "TextViewCategory.onClich()", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        mTextViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
            }
        });

        mTextViewRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewReleaseActivity.class));
            }
        });

        // Set onLongClickListener to the textView
        mTextViewRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RatingActivity.class));
            }
        });

        mTextViewClassification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClassificationActivity.class);
                intent.putExtra("first_name_str", "Everton");
                intent.putExtra("last_name_str", "Guimaraes");
                startActivity(intent);
            }
        });

        // Set onTouch listener to the ImaveView
        mImageViewProfile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // Option 1: Use hardcoded string OR
                //Snackbar.make(mConstraintLayout, "MainActivity.onTouchListener()", Snackbar.LENGTH_LONG);

                // Option 2: Make it more flexible.
                Snackbar.make(mConstraintLayout, R.string.main_image_view_msg, Snackbar.LENGTH_LONG).show();
                return true;
            }
        });

        // Set onClickListener to the Contact Button

        mButtonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "evertonguimaraes@gmail.com");
                intent.putExtra(Intent.EXTRA_CC, "ezt157@psu.edu");
                intent.putExtra(Intent.EXTRA_SUBJECT, "New Movies - Release");
                intent.putExtra(Intent.EXTRA_TEXT, "Please, notify me when new movies are available.");
                startActivity(Intent.createChooser(intent, "Send email to movie theater"));
            }
        });

    }

    // Statically defined method for handling event.
    public void clickRating(View view){
        Toast.makeText(getApplicationContext(), "MainActivity.clickRating", Toast.LENGTH_SHORT).show();
    }

    // Other lifecycle methods
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy()");
    }
}
