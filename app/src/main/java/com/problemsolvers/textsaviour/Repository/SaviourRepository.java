package com.problemsolvers.textsaviour.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.problemsolvers.textsaviour.Dao.DaoSaviour;
import com.problemsolvers.textsaviour.Data.DatabaseSaviour;
import com.problemsolvers.textsaviour.Saviour;

import java.util.List;

public class SaviourRepository {
    private DaoSaviour daoSaviour;
    private LiveData<List<Saviour>> allSaviour;

    public SaviourRepository(Application application) {
        DatabaseSaviour db = DatabaseSaviour.getINSTANCE(application);
        daoSaviour = db.daoSaviour();
        allSaviour = daoSaviour.getAllSaviour();

    }

    public LiveData<List<Saviour>> getAllSaviour() {
        return allSaviour;

    }

    public void insert(Saviour saviour) {
            new insertAsyncTask(daoSaviour).execute(saviour);

    }

    public void update(Saviour saviour) {
        new UpdateAsyncTask(daoSaviour).execute(saviour);

    }

    public void delete(Saviour saviour) {
        new deleteAsyncTask(daoSaviour).execute(saviour);

    }

    public void deleteAll() {
        new DeleteAllAsyncTask(daoSaviour).execute();

    }
    private static class insertAsyncTask extends AsyncTask<Saviour, Void, Void> {
        private DaoSaviour daoSaviour;

        public insertAsyncTask(DaoSaviour daoSaviour) {
            this.daoSaviour = daoSaviour;
        }

        @Override
        protected Void doInBackground(Saviour... saviours) {
            daoSaviour.insert(saviours[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Saviour, Void, Void> {
        private DaoSaviour daoSaviour;

        public UpdateAsyncTask(DaoSaviour daoSaviour) {
            this.daoSaviour = daoSaviour;
        }

        @Override
        protected Void doInBackground(Saviour... saviours) {
            daoSaviour.update(saviours[0]);
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<Saviour, Void, Void> {
        private DaoSaviour daoSaviour;

        public deleteAsyncTask(DaoSaviour daoSaviour) {
            this.daoSaviour = daoSaviour;
        }

        @Override
        protected Void doInBackground(Saviour... notes) {
            daoSaviour.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllAsyncTask extends AsyncTask<Saviour, Void, Void> {
        private DaoSaviour noteDao;

        public DeleteAllAsyncTask(DaoSaviour notedao) {
            this.noteDao = notedao;
        }

        @Override
        protected Void doInBackground(Saviour... notes) {
            noteDao.deleteAllSaviour();
            return null;
        }
    }

}