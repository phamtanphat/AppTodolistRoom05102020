package com.example.apptodolistroom05102020.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptodolistroom05102020.database.WordEntity;
import com.example.apptodolistroom05102020.repository.WordRepository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mWordRepo;
    private MutableLiveData<List<WordEntity>> mWords;
    private MutableLiveData<Long> mIdInsert;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mWords = new MutableLiveData<>();
        mIdInsert = new MutableLiveData<>();
        mWordRepo = WordRepository.getInstance(application);
    }

    public void fetchWords(){
        mWordRepo
                .getAllWords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<WordEntity>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull List<WordEntity> wordEntities) {
                        mWords.setValue(wordEntities);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<WordEntity>> getWords(){
        return mWords;
    }

    public void insertWord(WordEntity wordEntity){
        mWordRepo
                .insertWord()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Long aLong) {
                        mIdInsert.setValue(aLong);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Long> getIdInsert(){
        return mIdInsert;
    }
}
