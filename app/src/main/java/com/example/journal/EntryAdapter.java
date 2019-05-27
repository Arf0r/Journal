package com.example.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;


public class EntryAdapter extends ResourceCursorAdapter {

    // Constructor for entry adapter
    EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Save the incoming variables
        int title =  cursor.getColumnIndex("Title");
        String AdapterTitle = cursor.getString(title);
        int time = cursor.getColumnIndex("Time");
        String AdapterTime = cursor.getString(time);
        int mood =  cursor.getColumnIndex("Mood");
        String AdapterMood = cursor.getString(mood);

        // Set the textviews in the adapter
        TextView titleEntry = view.findViewById(R.id.titleEntry);
        titleEntry.setText(AdapterTitle);
        TextView timeStamp = view.findViewById(R.id.timeEntry);
        timeStamp.setText(String.valueOf(AdapterTime));
        ImageView image = (ImageView) view.findViewById(R.id.imageView2);

        // based on the mood string, set the image view
        if (AdapterMood.equals("Feeling: In love <3")) {
            image.setImageResource(R.drawable.love);
        }
        else if (AdapterMood.equals("Feeling: Neutral :|")) {
            image.setImageResource(R.drawable.neutral);
        }
        else if (AdapterMood.equals("Feeling: Sad :(")) {
            image.setImageResource(R.drawable.sad);
        }
        else if (AdapterMood.equals("Feeling: Happy :)")) {
            image.setImageResource(R.drawable.happy);
        }


    }
}
