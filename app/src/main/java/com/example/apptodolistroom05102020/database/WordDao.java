package com.example.apptodolistroom05102020.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;


@Dao
public interface WordDao {

    @Query("SELECT * FROM Word ")
    Single<List<WordEntity>> getAllWords();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Maybe<Long> insertWord(WordEntity wordEntity);

    @Update
    Maybe<Integer> updateWord(WordEntity wordEntity);
}
