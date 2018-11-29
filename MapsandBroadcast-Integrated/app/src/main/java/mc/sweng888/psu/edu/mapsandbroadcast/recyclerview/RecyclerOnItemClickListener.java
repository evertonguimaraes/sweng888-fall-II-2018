package mc.sweng888.psu.edu.mapsandbroadcast.recyclerview;

import android.view.View;

public interface RecyclerOnItemClickListener {

    void onItemClick(View view, int position);

    void onLongItemClick(View view, int position);
}
