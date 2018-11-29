package mc.sweng888.psu.edu.mapsandbroadcast.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerVIewItemClickListener implements RecyclerView.OnItemTouchListener {

    private RecyclerOnItemClickListener onItemClickListener;


    GestureDetector gestureDetector;

    public RecyclerVIewItemClickListener(Context context, final RecyclerView recyclerView, RecyclerOnItemClickListener listener) {

        onItemClickListener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && onItemClickListener != null) {
                    onItemClickListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    // It will intercept any touch event trigged by the RecyclerView
    @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {

        View childView = view.findChildViewUnder(e.getX(), e.getY());

        if (childView != null && onItemClickListener != null && gestureDetector.onTouchEvent(e)) {
            onItemClickListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        // Here goes your code.
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){
        // Here goes your code.
    }
}
