package com.example.ticktacktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ticktacktoe.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {
    ActivityScoreBinding mScoreBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScoreBinding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(mScoreBinding.getRoot());
    }
}