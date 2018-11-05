package com.example.mieib.popularmoviestage2.Data.Local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favorite_table")
public class Favorite {

    @PrimaryKey
    private int id;
    private String title;
    private String poster;
    private String synopsis;
    private String rating;
    private String  release_date;



    public Favorite(int id,String title,String poster,String synopsis,String rating,String release_date) {
        this.title = title;
        this.id = id;
        this.poster=poster;
        this.synopsis=synopsis;
        this.rating=rating;
        this.release_date=release_date;

    }


    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getPoster() {
        return poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getRating() {
        return rating;
    }

    public String getRelease_date() {
        return release_date;
    }
}
