package com.example.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        JournalEntry journal = (JournalEntry) intent.getSerializableExtra("journalEntry");

        TextView title = findViewById(R.id.titleDetail);
        TextView content = findViewById(R.id.contentDetail);
        ImageView happyDetail = findViewById(R.id.happyDetail);
        ImageView sadDetail = findViewById(R.id.sadDetail);
        ImageView neutralDetail = findViewById(R.id.neutralDetail);
        ImageView loveDetail = findViewById(R.id.loveDetail);

        title.setText(journal.getTitle());
        content.setText(journal.getContent());
        String mood = (String) journal.getMood();

        if (mood.equals("Feeling: Happy :)")){
            happyDetail.setVisibility(View.VISIBLE);
        }
        if (mood.equals("Feeling: In love <3")){
            loveDetail.setVisibility(View.VISIBLE);
        }
        if (mood.equals("Feeling: Sad :(")){
            sadDetail.setVisibility(View.VISIBLE);
        }
        if (mood.equals("Feeling: Neutral :|")){
            neutralDetail.setVisibility(View.VISIBLE);
        }
    }

    public void ButtonClicked(View view) {
        Intent intentInput = new Intent(DetailActivity.this, InputActivity.class);
        startActivity(intentInput);
    }
}
