package movies.sweng888.psu.edu.moviesapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import movies.sweng888.psu.edu.moviesapp.R;
import movies.sweng888.psu.edu.moviesapp.adapter.MovieAdapter;
import movies.sweng888.psu.edu.moviesapp.model.entity.dao.PersistenceMovie;
import movies.sweng888.psu.edu.moviesapp.model.entity.entity.Movie;

public class CategoryActivity extends AppCompatActivity {

    private ListView listViewMoviesCategory;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movies;

    private PersistenceMovie persistenceMovie = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Log.d("CategoryActivity", "onCreate");

        listViewMoviesCategory = (ListView) findViewById(R.id.list_view_movie_category);
        //movies = loadMovies();

        persistenceMovie = new PersistenceMovie(this);
        movies = persistenceMovie.getDataFromDB();

        movieAdapter = new MovieAdapter(this,
                R.layout.custom_list_item,
                movies);

        listViewMoviesCategory.setAdapter(movieAdapter);

        listViewMoviesCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Movie movie = (Movie) listViewMoviesCategory.getItemAtPosition(position);
                Intent intent = new Intent(CategoryActivity.this, MovieDetailsActivity.class);
                intent.putExtra("TITLE", movie.getTitle());
                intent.putExtra("CATEGORY", movie.getCategory());
                intent.putExtra("YEAR", movie.getYear());

                startActivity(intent);
            }
        });

    }

    private ArrayList<Movie> loadMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Black Panther", "Action", "2018"));
        movies.add(new Movie("Avengers I","Action","2010"));
        movies.add(new Movie("Avengers II", "Action","2014"));
        movies.add(new Movie("Avengers III", "Action","2016"));
        movies.add(new Movie("Avengers IV","Action", "2018"));
        movies.add(new Movie("Wonder Woman", "Action","2016"));
        movies.add(new Movie("Spider Man I", "Action","2009"));
        movies.add(new Movie("Spider Man II","Action", "2012"));
        movies.add(new Movie("Spider Man II","Action", "2014"));
        movies.add(new Movie("Black Panther", "Action","2018"));

        for (Movie movie : movies){
            persistenceMovie.insert(movie);
        }

        return  movies;
    }
}
