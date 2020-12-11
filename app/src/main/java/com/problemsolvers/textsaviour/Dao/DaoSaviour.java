package com.problemsolvers.textsaviour.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.problemsolvers.textsaviour.Saviour;

import java.util.List;

@Dao
public interface  DaoSaviour {

    @Insert
    void insert(Saviour saviour);

    @Delete
    void delete(Saviour saviour);

    @Update
    void update(Saviour saviour);

    @Query(" DELETE FROM saviour_table")
    void deleteAllSaviour();

    @Query("SELECT * FROM SAVIOUR_TABLE ORDER BY id DESC")
    LiveData<List<Saviour>> getAllSaviour();
}
