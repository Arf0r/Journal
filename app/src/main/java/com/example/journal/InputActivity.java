package com.example.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class InputActivity extends AppCompatActivity {
    TextView mood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        mood = findViewById(R.id.moodDetail);
    }

    public void submitClicked(View view) {



        TextView inputTitle = findViewById(R.id.titleDetail);
        String title = inputTitle.getText().toString();

        TextView inputContent = findViewById(R.id.contentDetail);
        String content = inputContent.getText().toString();

        TextView inputMood = findViewById(R.id.moodDetail);
        String mood = inputMood.getText().toString();

        Date currentTime = Calendar.getInstance().getTime();
        String time = String.valueOf(currentTime);

        JournalEntry journalEntry = new JournalEntry(0, title, content, mood, time);

        // Get the database and insert the entry.
        EntryDatabase.getInstance(this).insert(journalEntry);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    // If a mood button is clicked, change the text of the moodInput textview
    public void happyClick(View view) {
        mood.setText("Feeling: Happy :)");
    }

    public void neutralClick(View view) {
        mood.setText("Feeling: Neutral :|");
    }

    public void sadClick(View view) {
        mood.setText("Feeling: Sad :(");
    }

    public void loveClick(View view) {
        mood.setText("Feeling: In love <3");
    }

    // Save text mood
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("mood", mood.getText().toString());
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String input = savedInstanceState.getString(("mood"));
        mood.setText(input);
    }

}

