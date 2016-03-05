package com.example.erenp.notesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.erenp.notesapp.adapters.NoteAdapter;
import com.example.erenp.notesapp.clients.NoteRestClient;
import com.example.erenp.notesapp.models.Note;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class MainActivity extends AppCompatActivity {

    private ListView noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNotes();
    }

    private void getNotes() {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));

        NoteRestClient.get(MainActivity.this, "api/notes", headers.toArray(new Header[headers.size()]),
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        ArrayList<Note> noteArray = new ArrayList<Note>();
                        NoteAdapter noteAdapter = new NoteAdapter(MainActivity.this, noteArray);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                noteAdapter.add(new Note(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        noteList = (ListView) findViewById(R.id.list_notes);
                        noteList.setAdapter(noteAdapter);
                    }
                });
    }
}
