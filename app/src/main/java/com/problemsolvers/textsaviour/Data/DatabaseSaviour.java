package com.problemsolvers.textsaviour.Data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.problemsolvers.textsaviour.Dao.DaoSaviour;
import com.problemsolvers.textsaviour.Saviour;


@Database(entities = {Saviour.class}, version = 1)
public abstract class DatabaseSaviour extends RoomDatabase {

    public abstract DaoSaviour daoSaviour();
    public static  volatile DatabaseSaviour INSTANCE;

    public static DatabaseSaviour getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseSaviour.class) {
                if (INSTANCE == null) {
                    //create our db
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseSaviour.class, "saviour_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulatedbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulatedbAsyncTask extends AsyncTask<Void,Void,Void> {
        private DaoSaviour daoSaviour;
        private PopulatedbAsyncTask(DatabaseSaviour db){
            daoSaviour = db.daoSaviour();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            daoSaviour.insert(new Saviour("Gmail1","Rahul Sharma","pa8de#d"));
            daoSaviour.insert(new Saviour("Gmail 2","DRahul Sharma","pa8de#d"));
            daoSaviour.insert(new Saviour("Gmail 3","Description 3","pa8de#d"));
            return null;
        }
    }
}



