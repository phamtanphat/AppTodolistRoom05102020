package com.example.apptodolistroom05102020.repository;

import android.content.Context;

import com.example.apptodolistroom05102020.database.DatabaseInit;
import com.example.apptodolistroom05102020.database.WordDao;

import io.reactivex.rxjava3.core.Observable;

public class WordRepository {
    private static WordRepository instance = null;
    private WordDao wordDao;

    private WordRepository(Context context){
        wordDao = DatabaseInit.createDatabase(context).wordDao;
    }

    public static WordRepository getInstance(Context context){
        if (instance == null){
            instance = new WordRepository(context);
        }
        return instance;
    }

    public Observable<List<WordEntity>> getAllWords(){
        return wordDao.getAllWords();
    }
}
