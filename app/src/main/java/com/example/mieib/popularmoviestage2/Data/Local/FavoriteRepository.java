package com.example.mieib.popularmoviestage2.Data.Local;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class FavoriteRepository {

    private FavoriteDao noteDao;
    private LiveData<List<Favorite>> allNotes;
    private LiveData<Favorite> getFavoriteMovie;

    public FavoriteRepository(Application application){
        FavoriteDatabase database = FavoriteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();


    }



    public void insert(Favorite note){
        new InsertNoteAsynTask(noteDao).execute(note);
    }

    public void update(Favorite note){
        new UpdateNoteAsynTask(noteDao).execute(note);
    }

    public void delete(Favorite note){
        new DeleteNoteAsynTask(noteDao).execute(note);
    }

    public void deleteall(){
        new DeleteAllNoteAsynTask(noteDao).execute();
    }

    public LiveData<List<Favorite>> getAllNotes(){
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        return allNotes;
    }


    public LiveData<Favorite> getNote(int id){
        getFavoriteMovie = noteDao.getNote(id);
        return getFavoriteMovie;

    }


    private static class InsertNoteAsynTask extends AsyncTask<Favorite,Void,Void>{

        private FavoriteDao noteDao;

        private InsertNoteAsynTask(FavoriteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Favorite... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


    private static class UpdateNoteAsynTask extends AsyncTask<Favorite,Void,Void>{

        private FavoriteDao noteDao;

        private UpdateNoteAsynTask(FavoriteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Favorite... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsynTask extends AsyncTask<Favorite,Void,Void>{

        private FavoriteDao noteDao;

        private DeleteNoteAsynTask(FavoriteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Favorite... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


    private static class DeleteAllNoteAsynTask extends AsyncTask<Void,Void,Void>{

        private FavoriteDao noteDao;

        private DeleteAllNoteAsynTask(FavoriteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... notes) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

/*
    private static class getFavoriteAsynTask extends AsyncTask<Integer,Void,Void>{

        private FavoriteDao noteDao;

        private getFavoriteAsynTask(FavoriteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Integer... notes) {
            noteDao.getNote(notes[0]);
            return null;
        }
    }
*/
    /*
    private static class PopulatedAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;

        private PopulatedAsyncTask(NoteDatabase db){
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Favorite("Title 1","Description 1 ",1));
            noteDao.insert(new Favorite("Title 2","Description 2 ",2));
            noteDao.insert(new Favorite("Title 3","Description 3 ",3));

            return null;
        }
    }
*/
}
