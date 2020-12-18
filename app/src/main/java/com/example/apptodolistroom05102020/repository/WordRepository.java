package com.example.apptodolistroom05102020.repository;

import android.app.Application;
import android.content.Context;

import com.example.apptodolistroom05102020.database.DatabaseInit;
import com.example.apptodolistroom05102020.database.WordDao;
import com.example.apptodolistroom05102020.database.WordEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;


public class WordRepository {
    private static WordRepository instance = null;
    private WordDao wordDao;

    private WordRepository(Application application){
        wordDao = DatabaseInit.createDatabase(application.getBaseContext()).wordDao();
    }

    public static WordRepository getInstance(Application application){
        if (instance == null){
            instance = new WordRepository(application);
        }
        return instance;
    }

    public Single<List<WordEntity>> getAllWords(){
        return wordDao.getAllWords();
    }
    public Maybe<Long> insertWord(WordEntity wordEntity){
        return wordDao.insertWord(wordEntity);
    }

    public Maybe<Integer> updateWord(WordEntity wordEntity){
        return wordDao.updateWord(wordEntity);
    }
}
