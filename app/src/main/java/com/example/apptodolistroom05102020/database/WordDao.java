package com.example.apptodolistroom05102020.database;

import androidx.room.Dao;
import androidx.room.Query;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface WordDao {

    @Query("SELECT * FROM Word")
    Observable<List<WordEntity>> getAllWords();

}
