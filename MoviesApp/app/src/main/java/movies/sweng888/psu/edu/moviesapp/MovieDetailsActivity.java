package movies.sweng888.psu.edu.moviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        textView = (TextView) findViewById(R.id.details_text_view_title);

        Intent intent = getIntent();

        String title = intent.getStringExtra("TITLE");
        String category = intent.getStringExtra("CATEGORY");
        String year = intent.getStringExtra("YEAR");

        StringBuilder builder = new StringBuilder(50);
        builder.append("Movie: "+title);
        builder.append(" ("+category+") ");
        builder.append("Year - "+year);
        textView.setText(builder);
    }
}
