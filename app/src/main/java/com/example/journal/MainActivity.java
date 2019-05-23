package com.example.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Create variables
    FloatingActionButton floatingActionButton;
    ListView listView;
    EntryDatabase EntryDatabase;
    EntryAdapter EntryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        // Get the information in the database and adapt the visualization according to EntryAdapter
        EntryDatabase = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = EntryDatabase.selectAll();
        EntryAdapter = new EntryAdapter(MainActivity.this, cursor);

        // Put the infomation in the listView
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(EntryAdapter);

        // Activate the listeners
        listView.setOnItemClickListener(new checkItemClick());
        listView.setOnItemLongClickListener(new LongItemClickListener());
    }

    public void checkClicked (View view) {
        // Check if button for new entry is being clicked, if so start inputActivity, stop current activity
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
        finish();

    }
    private class checkItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor clickedEntry = (Cursor) parent.getItemAtPosition(position);

            JournalEntry journalEntry = new JournalEntry(
                    clickedEntry.getInt(clickedEntry.getColumnIndex("_id")),
                    clickedEntry.getString(clickedEntry.getColumnIndex("Title")),
                    clickedEntry.getString(clickedEntry.getColumnIndex("Content")),
                    clickedEntry.getString(clickedEntry.getColumnIndex("Mood")),
                    clickedEntry.getString(clickedEntry.getColumnIndex("Time")));

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("journalEntry", journalEntry);

            startActivity(intent);
        }
    }

    private class LongItemClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor deletedEntry = (Cursor) parent.getItemAtPosition(position);
            EntryDatabase.delete(deletedEntry.getInt(deletedEntry.getColumnIndex("_id")));

            EntryAdapter.swapCursor(EntryDatabase.selectAll());
            return true;
        }
    }
}

