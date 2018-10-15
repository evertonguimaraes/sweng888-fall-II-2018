package movies.sweng888.psu.edu.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextViewMovieRating;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the content of activity to use activity_main.xml layout file.
        setContentView(R.layout.activity_main);

        // sets the reference to the layout element that will be handled by java code
        mTextViewMovieRating = (TextView) findViewById(R.id.movie_rating);

        mTextViewMovieRating.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Go to next page.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, RatingActivity.class);
        startActivity(intent);
    }
}
