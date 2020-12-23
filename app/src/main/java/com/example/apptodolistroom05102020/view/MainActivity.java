package com.example.apptodolistroom05102020.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.apptodolistroom05102020.R;
import com.example.apptodolistroom05102020.database.WordEntity;
import com.example.apptodolistroom05102020.interfaces.OnListenLoading;
import com.example.apptodolistroom05102020.view.adapter.WordAdapter;
import com.example.apptodolistroom05102020.viewmodel.WordViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnListenLoading {
    WordViewModel mViewModel;
    TextInputEditText mTextInputEn, mTextInputVn;
    Button mBtnAddWord,mBtnCancel, mBtnOpenForm;
    LinearLayout mLinearLayoutLoading;
    Spinner mSpinnerFilter;
    CardView mCarForm;
    PullRefreshLayout mPullRefreshLayout;
    RecyclerView mRcvWord;
    WordAdapter mWordAdapter;
    boolean isOpenForm = false;
    boolean mIsLoading = false;
    List<WordEntity> mWordEntities;
    OnListenLoading mOnListenLoading;
    ArrayAdapter mAdapterSpinner;
    String[] mArrayFilter = {"SHOW ALL" , "SHOW FORGOT" , "SHOW MEMORIZED"};
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
        mLinearLayoutLoading = findViewById(R.id.linearLoading);
        mRcvWord = findViewById(R.id.recyclerViewWord);
        mWordEntities = new ArrayList<>();
        mWordAdapter = new WordAdapter(mWordEntities);
        //Data
        mViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(WordViewModel.class);
        mAdapterSpinner = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item,mArrayFilter);
        // interface
        mOnListenLoading = this::onLoading;
    }
    private void mapView() {
        if (isOpenForm){
            showView(mCarForm);
            hideView(mBtnOpenForm);
        }else{
            showView(mBtnOpenForm);
            hideView(mCarForm);
        }
        mOnListenLoading.onLoading(mIsLoading);
        mRcvWord.setAdapter(mWordAdapter);
        mSpinnerFilter.setAdapter(mAdapterSpinner);
    }
    private void observerData() {
        mViewModel.getWords().observe(this, new Observer<List<WordEntity>>() {
            @Override
            public void onChanged(List<WordEntity> wordEntities) {
                if (mIsLoading){
                    mIsLoading = false;
                    mOnListenLoading.onLoading(mIsLoading);
                }
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
        mPullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mIsLoading = true;
                mOnListenLoading.onLoading(mIsLoading);
                mWordEntities.clear();
                mWordAdapter.notifyDataSetChanged();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewModel.fetchWords();
                    }
                },2000);


            }
        });
        mSpinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, mArrayFilter[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void hideView(View view){
        view.setVisibility(View.GONE);
    }
    private void showView(View view){
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoading(Boolean isLoading) {
        if (mIsLoading) {
            showView(mLinearLayoutLoading);
        }else{
            hideView(mLinearLayoutLoading);
        }
        mPullRefreshLayout.setRefreshing(isLoading);
    }
}
// Show forgot : Những từ đã thuộc (ismemorized = true) hiển thị
// Show memorized : Những từ chưa thuộc (ismemorized = false) hiển thị