package com.example.erenp.notesapp.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {

    private String Id;
    private String Title;
    private String Content;

    public Note(JSONObject object) {
        try {
            this.Id = object.getString("id");
            this.Title = object.getString("title");
            this.Content = object.getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Note(String Title, String Content) {
        this.Title = Title;
        this.Content = Content;
    }

    public String getId() {
        return this.Id;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getContent() {
        return this.Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }
}