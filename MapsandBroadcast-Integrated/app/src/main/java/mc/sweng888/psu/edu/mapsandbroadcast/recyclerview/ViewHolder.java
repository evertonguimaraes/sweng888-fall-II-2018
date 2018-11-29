package mc.sweng888.psu.edu.mapsandbroadcast.recyclerview;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mc.sweng888.psu.edu.mapsandbroadcast.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    // Elements defined in the UI
    private CardView cardView;

    private ImageView imageView;

    TextView textViewLatitude;
    TextView textViewLongitude;
    TextView textViewLocation;

    public ViewHolder( View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardView);
        textViewLatitude = itemView.findViewById(R.id.txt_view_latitute);
        textViewLongitude = itemView.findViewById(R.id.txt_view_longitute);
        textViewLocation =  itemView.findViewById(R.id.text_view_location);
        imageView = itemView.findViewById(R.id.image_view_location);
    }
}
