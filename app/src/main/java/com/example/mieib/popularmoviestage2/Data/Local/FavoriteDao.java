package com.example.mieib.popularmoviestage2.Data.Local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insert(Favorite note);

    @Update
    void update(Favorite note);

    @Delete
    void delete(Favorite note);

    @Query("DELETE from favorite_table")
    void deleteAllNotes();

    @Query("select * from favorite_table")
    LiveData<List<Favorite>> getAllNotes();

    @Query("select * from favorite_table where id = :id")
    LiveData<Favorite> getNote(int id);


}
