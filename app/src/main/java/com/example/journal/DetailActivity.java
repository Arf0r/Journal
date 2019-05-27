package com.example.journal;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView title;
    TextView content;
    ImageView happyDetail;
    ImageView sadDetail;
    ImageView neutralDetail;
    ImageView loveDetail;

    // Show the user the activity detail layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Save the intent and the incoming journalentry
        Intent intent = getIntent();
        JournalEntry journal = (JournalEntry) intent.getSerializableExtra("journalEntry");

        // Find the textviews and image views
        title = findViewById(R.id.titleDetail);
        content = findViewById(R.id.contentDetail);
        happyDetail = findViewById(R.id.happyDetail);
        sadDetail = findViewById(R.id.sadDetail);
        neutralDetail = findViewById(R.id.neutralDetail);
        loveDetail = findViewById(R.id.loveDetail);

        // Set the text in the textviews according to the incoming journal entry
        title.setText(journal.getTitle());
        content.setText(journal.getContent());
        String mood = (String) journal.getMood();

        // Set the imageview visibility based on the mood string
        if (mood.equals("Feeling: Happy :)")) {
            happyDetail.setVisibility(View.VISIBLE);
        }
        if (mood.equals("Feeling: In love <3")) {
            loveDetail.setVisibility(View.VISIBLE);
        }
        if (mood.equals("Feeling: Sad :(")) {
            sadDetail.setVisibility(View.VISIBLE);
        }
        if (mood.equals("Feeling: Neutral :|")) {
            neutralDetail.setVisibility(View.VISIBLE);
        }
    }

    // Save for variables when device is rotated
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", title.getText().toString());
        outState.putSerializable("content", content.getText().toString());
        outState.putInt("happy", happyDetail.getVisibility());
        outState.putInt("sad", (sadDetail.getVisibility()));
        outState.putInt("neutral",(neutralDetail.getVisibility()));
        outState.putInt("love", (loveDetail.getVisibility()));

    }

    // Reset the variables after rotation
    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        title.setText(inState.getString("title"));
        content.setText(inState.getString("content"));
        happyDetail.setVisibility(inState.getInt("happy"));
        sadDetail.setVisibility(inState.getInt("sad"));
        neutralDetail.setVisibility(inState.getInt("neutral"));
        loveDetail.setVisibility(inState.getInt("love"));

    }
}
