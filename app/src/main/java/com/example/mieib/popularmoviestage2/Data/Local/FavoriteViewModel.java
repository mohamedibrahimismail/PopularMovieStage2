package com.example.mieib.popularmoviestage2.Data.Local;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private FavoriteRepository repository;
    private LiveData<List<Favorite>> allNotes;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        repository = new FavoriteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Favorite note){
        repository.insert(note);
    }

    public void update(Favorite note){
        repository.update(note);
    }

    public void delete(Favorite note){
        repository.delete(note);
    }

    public void deleteAllNote(){
        repository.deleteall();
    }

    public LiveData<List<Favorite>> getAllNotes(){
        return allNotes;
    }

    public LiveData<Favorite> getFavoriteMovie(int id){ return repository.getNote(id);}

}
