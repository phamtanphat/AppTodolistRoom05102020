package com.example.apptodolistroom05102020.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptodolistroom05102020.database.WordEntity;
import com.example.apptodolistroom05102020.repository.WordRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mWordRepo;
    private MutableLiveData<List<WordEntity>> mWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mWords = new MutableLiveData<>();
        mWordRepo = WordRepository.getInstance(application.getApplicationContext());
    }

    public void fetchWords(){
        mWordRepo
                .getAllWords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<WordEntity>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<WordEntity> wordEntities) {
                        mWords.setValue(wordEntities);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
