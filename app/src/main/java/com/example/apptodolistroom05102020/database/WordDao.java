package com.example.apptodolistroom05102020.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;


@Dao
public interface WordDao {

    @Query("SELECT * FROM Word ")
    Observable<List<WordEntity>> getAllWords();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Maybe<Long> insertWord(WordEntity wordEntity);

}
