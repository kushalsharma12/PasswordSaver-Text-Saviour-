package com.problemsolvers.textsaviour.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.problemsolvers.textsaviour.Repository.SaviourRepository;
import com.problemsolvers.textsaviour.Saviour;

import java.util.List;

public class SaviourViewModel extends AndroidViewModel {

    private LiveData<List<Saviour>> allSaviour;
    private SaviourRepository saviourRepository;


    public SaviourViewModel(@NonNull Application application) {
        super(application);
        saviourRepository = new SaviourRepository(application);
        allSaviour = saviourRepository.getAllSaviour();

    }

    public void insert(Saviour saviour) {
        saviourRepository.insert(saviour);
    }

    public void update(Saviour saviour) {
        saviourRepository.update(saviour);
    }

    public void delete(Saviour saviour) {
        saviourRepository.delete(saviour);
    }

    public void deleteAll() {
        saviourRepository.deleteAll();
    }

    public LiveData<List<Saviour>> getAllSaviour() {
        return allSaviour;
    }


}
