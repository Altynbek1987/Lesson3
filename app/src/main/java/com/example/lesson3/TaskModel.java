package com.example.lesson3;

import java.io.Serializable;

public class TaskModel implements Serializable {
    private String title, description, date;

    public TaskModel( String title, String description,String date) {
        this.title = title;
        this.description = description;
        this.date = date;
        //this.data = data;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
