package movies.sweng888.psu.edu.moviesapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import movies.sweng888.psu.edu.moviesapp.R;
import movies.sweng888.psu.edu.moviesapp.model.entity.entity.Movie;

public class MovieAdapter extends ArrayAdapter {

    public MovieAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // It recover the view to the inflate

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_list_item, parent, false);
        }

        // Get the object located at this position in the list
        Movie movie = (Movie) getItem(position);

        // Find the TextView in the xml layout wit the ID
        TextView textViewTitle = (TextView) listItemView.findViewById(R.id.text_view_movie_title);

        // Get the title name from the current Movie object and
        // set this text on the name TextView
        textViewTitle.setText(movie.getTitle());


        TextView textViewCategory = (TextView) listItemView.findViewById(R.id.text_view_movie_category);

        // Get the year number from the current Movie object and
        // set this text on the number TextView
        textViewCategory.setText(movie.getCategory());

        // Find the TextView in the xml layout with the ID version_number
        TextView textViewYear = (TextView) listItemView.findViewById(R.id.text_view_movie_year);

        // Get the year number from the current Movie object and
        // set this text on the number TextView
        textViewYear.setText(movie.getYear());


        // TODO You can also customize the ImageView.

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;


    }
}
