package com.example.ticktacktoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.ticktacktoe.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {
    ActivityScoreBinding mBinding;
    HistoryAdapter mHistoryAdapter;
    HistoryManager mHistoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mHistoryManager = HistoryManager.buildWith(openOrCreateDatabase(HistoryManager.HISTORY_DATABASE, MODE_PRIVATE, null));

        mHistoryAdapter = new HistoryAdapter(mHistoryManager.getGameOverHistory());

        mBinding.historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.historyRecyclerView.setAdapter(mHistoryAdapter);
    }
}