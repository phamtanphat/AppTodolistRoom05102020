package com.example.apptodolistroom05102020.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.baoyz.widget.PullRefreshLayout;
import com.example.apptodolistroom05102020.R;
import com.example.apptodolistroom05102020.database.WordEntity;
import com.example.apptodolistroom05102020.view.adapter.WordAdapter;
import com.example.apptodolistroom05102020.viewmodel.WordViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    WordViewModel mViewModel;
    TextInputEditText mTextInputEn, mTextInputVn;
    Button mBtnAddWord,mBtnCancel, mBtnOpenForm;
    Spinner mSpinnerFilter;
    CardView mCarForm;
    PullRefreshLayout mPullRefreshLayout;
    RecyclerView mRcvWord;
    WordAdapter mWordAdapter;
    boolean isOpenForm = false;
    List<WordEntity> mWordEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mapView();
        observerData();
        event();
//        mViewModel.getResultDelete().observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//                if (aBoolean){
//                    Log.d("BBB","Xoa thanh cong");
//                }else {
//                    Log.d("BBB","Xoa that bai");
//                }
//            }
//        });
//        mViewModel.getWords().observe(this, new Observer<List<WordEntity>>() {
//            @Override
//            public void onChanged(List<WordEntity> wordEntities) {
////                Log.d("BBB",wordEntities.get(0).getId() + "");
//                mViewModel.deleteWord(3);
//            }
//        });
//
//        mViewModel.fetchWords();

//        mViewModel.getIdInsert().observe(this, new Observer<Long>() {
//            @Override
//            public void onChanged(Long aLong) {
//                Log.d("BBB","Id dong " + aLong);
//            }
//        });
//
//        mViewModel.insertWord(new WordEntity("One","Mot",0));
    }
    private void init() {
        // View
        mTextInputEn = findViewById(R.id.textinputEn);
        mTextInputVn = findViewById(R.id.textinputVn);
        mBtnAddWord = findViewById(R.id.buttonAddWord);
        mBtnCancel = findViewById(R.id.buttonCancel);
        mBtnOpenForm = findViewById(R.id.buttonOpenForm);
        mSpinnerFilter = findViewById(R.id.spinner);
        mCarForm = findViewById(R.id.carViewForm);
        mPullRefreshLayout = findViewById(R.id.pullRefreshLayout);
        mRcvWord = findViewById(R.id.recyclerViewWord);
        mWordEntities = new ArrayList<>();
        mWordAdapter = new WordAdapter(mWordEntities);
        //Data
        mViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(WordViewModel.class);
    }
    private void mapView() {
        if (isOpenForm){
            showView(mCarForm);
            hideView(mBtnOpenForm);
        }else{
            showView(mBtnOpenForm);
            hideView(mCarForm);
        }
        mRcvWord.setAdapter(mWordAdapter);
    }
    private void observerData() {
        mViewModel.getWords().observe(this, new Observer<List<WordEntity>>() {
            @Override
            public void onChanged(List<WordEntity> wordEntities) {
                mWordEntities.addAll(wordEntities);
                mWordAdapter.notifyDataSetChanged();
            }
        });
    }
    private void event() {
        mViewModel.fetchWords();
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView(mBtnOpenForm);
                hideView(mCarForm);
            }
        });
        mBtnOpenForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView(mCarForm);
                hideView(mBtnOpenForm);
            }
        });
    }

    private void hideView(View view){
        view.setVisibility(View.GONE);
    }
    private void showView(View view){
        view.setVisibility(View.VISIBLE);
    }
}