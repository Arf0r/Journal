package com.example.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class InputActivity extends AppCompatActivity {
    // Initialize variable
    TextView mood;

    // When the activity is started show mood detail layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        mood = findViewById(R.id.moodDetail);
    }

    // If submit button is clicked...
    public void submitClicked(View view) {

        // Save all the input from the user in Strings
        TextView inputTitle = findViewById(R.id.titleDetail);
        String title = inputTitle.getText().toString();
        TextView inputContent = findViewById(R.id.contentDetail);
        String content = inputContent.getText().toString();
        TextView inputMood = findViewById(R.id.moodDetail);
        String mood = inputMood.getText().toString();
        Date currentTime = Calendar.getInstance().getTime();
        String time = String.valueOf(currentTime);

        // Make a new journal entry object with the data from the user
        JournalEntry journalEntry = new JournalEntry(0, title, content, mood, time);

        // Insert the entry into the database
        EntryDatabase.getInstance(this).insert(journalEntry);

        // Start new activity (main)
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

    // Restore text mood
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String input = savedInstanceState.getString(("mood"));
        mood.setText(input);
    }

}

