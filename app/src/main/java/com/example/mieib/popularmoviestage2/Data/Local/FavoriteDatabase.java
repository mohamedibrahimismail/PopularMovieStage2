package com.example.mieib.popularmoviestage2.Data.Local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Favorite.class},version = 2,exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {

    private static FavoriteDatabase instance;

    public abstract FavoriteDao noteDao();

    public static synchronized FavoriteDatabase getInstance(Context context){

        if(instance==null){

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();

        }

        return instance;
    }

    private static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();

        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{

        private FavoriteDao noteDao;

        private PopulateDbAsyncTask(FavoriteDatabase db){
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
          /*  noteDao.insert(new Favorite("Title 1","Description 1",1));
            noteDao.insert(new Favorite("Title 2","Description 2",2));
            noteDao.insert(new Favorite("Title 3","Description 3",3));
*/
            return null;

        }
    }

}
