package com.example.ticktacktoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.ticktacktoe.databinding.ActivityDashBoardBinding;

public class DashBoardActivity extends AppCompatActivity {
    ActivityDashBoardBinding mDashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDashBinding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(mDashBinding.getRoot());

        mDashBinding.playBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this, MainActivity.class);
            startActivity(intent);
        });
        mDashBinding.scoreBtn.setOnClickListener(v -> {
            Intent intent= new Intent(DashBoardActivity.this, ScoreActivity.class);
            startActivity(intent);
        });
        mDashBinding.exitBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(DashBoardActivity.this)
                    .setMessage(R.string.do_you_want_to_exit)
                    .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            }).show();

        });
    }
}