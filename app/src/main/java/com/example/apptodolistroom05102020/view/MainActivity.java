package com.example.apptodolistroom05102020.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.apptodolistroom05102020.R;
import com.example.apptodolistroom05102020.database.WordEntity;
import com.example.apptodolistroom05102020.viewmodel.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(WordViewModel.class);

        mViewModel.getResultDelete().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Log.d("BBB","Xoa thanh cong");
                }else {
                    Log.d("BBB","Xoa that bai");
                }
            }
        });
        mViewModel.getWords().observe(this, new Observer<List<WordEntity>>() {
            @Override
            public void onChanged(List<WordEntity> wordEntities) {
//                Log.d("BBB",wordEntities.get(0).getId() + "");
                mViewModel.deleteWord(3);
            }
        });

        mViewModel.fetchWords();

//        mViewModel.getIdInsert().observe(this, new Observer<Long>() {
//            @Override
//            public void onChanged(Long aLong) {
//                Log.d("BBB","Id dong " + aLong);
//            }
//        });
//
//        mViewModel.insertWord(new WordEntity("One","Mot",0));


    }
}