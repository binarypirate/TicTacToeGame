package com.example.ticktacktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ticktacktoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.topLeft.setOnClickListener(v -> {

        });
        mBinding.topMid.setOnClickListener(v -> {

        });
        mBinding.topRight.setOnClickListener(v -> {

        });
        mBinding.midLeft.setOnClickListener(v -> {

        });
        mBinding.midMid.setOnClickListener(v -> {

        });
        mBinding.midRight.setOnClickListener(v -> {

        });
        mBinding.bottomLeft.setOnClickListener(v -> {

        });
        mBinding.bottomMid.setOnClickListener(v -> {

        });
        mBinding.bottomRight.setOnClickListener(v -> {

        });
    }
}