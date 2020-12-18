package com.example.apptodolistroom05102020.repository;

import android.app.Application;
import android.content.Context;

import com.example.apptodolistroom05102020.database.DatabaseInit;
import com.example.apptodolistroom05102020.database.WordDao;
import com.example.apptodolistroom05102020.database.WordEntity;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;


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

    public Observable<List<WordEntity>> getAllWords(){
        return wordDao.getAllWords();
    }
    public Maybe<Long> insertWord(WordEntity wordEntity){
        return wordDao.insertWord(wordEntity);
    }
}
