package com.example.apptodolistroom05102020.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptodolistroom05102020.R;
import com.example.apptodolistroom05102020.database.WordEntity;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordHolder> {

    List<WordEntity> mArrWords;
    Context mContext;
    public WordAdapter(List<WordEntity> mArrWords) {
        this.mArrWords = mArrWords;
    }

    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.layout_item_word, parent, false);
        return new WordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordHolder holder, int position) {
        WordEntity word = mArrWords.get(position);
        if (word.getIsmemorized() != 0) {
            holder.mTvVn.setText(mContext.getResources().getString(R.string.text_vn_empty));
            holder.mBtnMemorized.setText(mContext.getResources().getString(R.string.text_forgot));
            holder.mBtnMemorized.setBackground(mContext.getDrawable(R.drawable.box_corner_green));
        } else {
            holder.mTvVn.setText(word.getVn());
            holder.mBtnMemorized.setText(mContext.getResources().getString(R.string.text_memorized));
            holder.mBtnMemorized.setBackground(mContext.getDrawable(R.drawable.box_corner_red));
        }
        holder.mTvEn.setText(word.getEn());
    }

    @Override
    public int getItemCount() {
        return mArrWords != null ? mArrWords.size() : 0;
    }

    class WordHolder extends RecyclerView.ViewHolder {
        TextView mTvEn, mTvVn;
        Button mBtnMemorized, mBtnRemove;

        public WordHolder(@NonNull View itemView) {
            super(itemView);
            mTvVn = itemView.findViewById(R.id.textViewVn);
            mTvEn = itemView.findViewById(R.id.textViewEn);
            mBtnMemorized = itemView.findViewById(R.id.buttonMemorized);
            mBtnRemove = itemView.findViewById(R.id.buttonRemove);
        }
    }

}
