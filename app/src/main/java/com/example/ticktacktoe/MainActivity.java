package com.example.ticktacktoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ticktacktoe.TicTacToe.ColumnPosition;
import com.example.ticktacktoe.TicTacToe.RowPosition;
import com.example.ticktacktoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnGameWinListener {

    ActivityMainBinding mBinding;
    TicTacToe mTicTacToe;
    int mUser = newUser();
    ImageView[][] mWinningCircles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mTicTacToe = new TicTacToe(this);

        mWinningCircles = new ImageView[][]{
                {mBinding.topLeft, mBinding.topMid, mBinding.topRight},
                {mBinding.midLeft, mBinding.midMid, mBinding.midRight},
                {mBinding.bottomLeft, mBinding.bottomMid, mBinding.bottomRight}
        };

        mBinding.topLeft.setOnClickListener(v -> changeValue(((ImageView) v), RowPosition.TOP, ColumnPosition.LEFT));
        mBinding.topMid.setOnClickListener(v -> changeValue(((ImageView) v), RowPosition.TOP, ColumnPosition.MID));
        mBinding.topRight.setOnClickListener(v -> changeValue(((ImageView) v), RowPosition.TOP, ColumnPosition.RIGHT));
        mBinding.midLeft.setOnClickListener(v -> changeValue(((ImageView) v), RowPosition.MID, ColumnPosition.LEFT));
        mBinding.midMid.setOnClickListener(v -> changeValue(((ImageView) v), RowPosition.MID, ColumnPosition.MID));
        mBinding.midRight.setOnClickListener(v -> changeValue(((ImageView) v), RowPosition.MID, ColumnPosition.RIGHT));
        mBinding.bottomLeft.setOnClickListener(v -> changeValue(((ImageView) v), RowPosition.BOTTOM, ColumnPosition.LEFT));
        mBinding.bottomMid.setOnClickListener(v -> changeValue(((ImageView) v), RowPosition.BOTTOM, ColumnPosition.MID));
        mBinding.bottomRight.setOnClickListener(v -> changeValue(((ImageView) v), RowPosition.BOTTOM, ColumnPosition.RIGHT));
    }

    private void changeValue(ImageView v, RowPosition rowPosition, ColumnPosition columnPosition) {
        v.setImageResource(mUser == 0 ? R.drawable.ic_cross : R.drawable.ic_tick);
        mTicTacToe.setValue(mUser, rowPosition, columnPosition);
        mUser = mUser == 0 ? 1 : 0;
    }

    private int newUser() {
        return (int) Math.floor(Math.random() * (2));
    }

    @Override
    public void onGameWin(int winner, WinningDiagonal diagonal) {
        Toast.makeText(this, winner + " Won!", Toast.LENGTH_SHORT).show();
        switch (diagonal) {
            case TOP_HORIZONTAL:
                for (ImageView v : mWinningCircles[0]) v.setImageResource(R.drawable.ic_win);
                myDialog(winner);
                break;
            case MID_HORIZONTAL:
                for (ImageView v : mWinningCircles[1]) v.setImageResource(R.drawable.ic_win);
                myDialog(winner);
                break;
            case BOTTOM_HORIZONTAL:
                for (ImageView v : mWinningCircles[2]) v.setImageResource(R.drawable.ic_win);
                myDialog(winner);
                break;
            case START_VERTICAL:
                for (ImageView v : new ImageView[]{mWinningCircles[0][0], mWinningCircles[1][0], mWinningCircles[2][0]})
                    v.setImageResource(R.drawable.ic_win);
                myDialog(winner);
                break;
//                Todo: Check here
            case MID_VERTICAL:
                for (ImageView v : new ImageView[]{mWinningCircles[0][1], mWinningCircles[1][1], mWinningCircles[2][1]})
                    v.setImageResource(R.drawable.ic_win);
                myDialog(winner);
                break;
            case END_VERTICAL:
                for (ImageView v : new ImageView[]{mWinningCircles[0][2], mWinningCircles[1][2], mWinningCircles[2][2]})
                    v.setImageResource(R.drawable.ic_win);
                myDialog(winner);
                break;
            case TOP_START_TO_BOTTOM_END_AXIS:
                for (ImageView v : new ImageView[]{mWinningCircles[0][0], mWinningCircles[1][1], mWinningCircles[2][2]})
                    v.setImageResource(R.drawable.ic_win);
                myDialog(winner);
                break;
            case TOP_END_TO_BOTTOM_START_AXIS:
                for (ImageView v : new ImageView[]{mWinningCircles[0][2], mWinningCircles[1][1], mWinningCircles[2][0]})
                    v.setImageResource(R.drawable.ic_win);

                myDialog(winner);
                break;
        }
    }

    enum WinningDiagonal {
        TOP_HORIZONTAL,
        MID_HORIZONTAL,
        BOTTOM_HORIZONTAL,
        START_VERTICAL,
        MID_VERTICAL,
        END_VERTICAL,
        TOP_START_TO_BOTTOM_END_AXIS,
        TOP_END_TO_BOTTOM_START_AXIS
    }

    public void myDialog(int position) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Player " + position + " Won the Game")
                .setPositiveButton(R.string.re_Strart, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mBinding.topLeft.setImageResource(0);
                        mBinding.topMid.setImageResource(0);
                        mBinding.topRight.setImageResource(0);
                        mBinding.midLeft.setImageResource(0);
                        mBinding.midRight.setImageResource(0);
                        mBinding.midMid.setImageResource(0);
                        mBinding.bottomLeft.setImageResource(0);
                        mBinding.bottomMid.setImageResource(0);
                        mBinding.bottomRight.setImageResource(0);
                        dialog.dismiss();

                    }
                }).setNegativeButton(R.string.go_back, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        }).setCancelable(false).show();
    }
}