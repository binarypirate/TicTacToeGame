package com.example.ticktacktoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ticktacktoe.TicTacToe.ColumnPosition;
import com.example.ticktacktoe.TicTacToe.RowPosition;
import com.example.ticktacktoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnGameEventListener {

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
        mBinding.winnerImage.setVisibility(View.INVISIBLE);
        mBinding.gameOverStatus.setVisibility(View.INVISIBLE);

        mWinningCircles = new ImageView[][]{
                {mBinding.topLeft, mBinding.topMid, mBinding.topRight},
                {mBinding.midLeft, mBinding.midMid, mBinding.midRight},
                {mBinding.bottomLeft, mBinding.bottomMid, mBinding.bottomRight}
        };

        mBinding.restartBtn.setOnClickListener(v -> playAgain());

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
        mBinding.turnImage.setImageResource(mUser == 1 ? R.drawable.ic_tick : R.drawable.ic_cross);
    }

    private int newUser() {
        return (int) Math.floor(Math.random() * (2));
    }

    @Override
    public void onGameWin(int winner, WinningDiagonal diagonal) {
        mBinding.winnerImage.setVisibility(View.VISIBLE);
        mBinding.gameOverStatus.setVisibility(View.VISIBLE);
        mBinding.winnerImage.setImageResource(winner == 1 ? R.drawable.ic_tick : R.drawable.ic_cross);
        mBinding.gameOverStatus.setText(R.string.won);
        mBinding.turnImage.setVisibility(View.GONE);
        mBinding.turnTextLabel.setVisibility(View.GONE);
        switch (diagonal) {
            case TOP_HORIZONTAL:
                for (ImageView v : mWinningCircles[0]) v.setImageResource(R.drawable.ic_win);
                break;
            case MID_HORIZONTAL:
                for (ImageView v : mWinningCircles[1]) v.setImageResource(R.drawable.ic_win);
                break;
            case BOTTOM_HORIZONTAL:
                for (ImageView v : mWinningCircles[2]) v.setImageResource(R.drawable.ic_win);
                break;
            case START_VERTICAL:
                for (ImageView v : new ImageView[]{mWinningCircles[0][0], mWinningCircles[1][0], mWinningCircles[2][0]})
                    v.setImageResource(R.drawable.ic_win);
                break;
            case MID_VERTICAL:
                for (ImageView v : new ImageView[]{mWinningCircles[0][1], mWinningCircles[1][1], mWinningCircles[2][1]})
                    v.setImageResource(R.drawable.ic_win);
                break;
            case END_VERTICAL:
                for (ImageView v : new ImageView[]{mWinningCircles[0][2], mWinningCircles[1][2], mWinningCircles[2][2]})
                    v.setImageResource(R.drawable.ic_win);
                break;
            case TOP_START_TO_BOTTOM_END_AXIS:
                for (ImageView v : new ImageView[]{mWinningCircles[0][0], mWinningCircles[1][1], mWinningCircles[2][2]})
                    v.setImageResource(R.drawable.ic_win);
                break;
            case TOP_END_TO_BOTTOM_START_AXIS:
                for (ImageView v : new ImageView[]{mWinningCircles[0][2], mWinningCircles[1][1], mWinningCircles[2][0]})
                    v.setImageResource(R.drawable.ic_win);
                break;
        }
        for (ImageView[] imagesRow: mWinningCircles) {
            for (ImageView image: imagesRow) {
                image.setEnabled(false);
            }
        }
    }

    private void playAgain() {
        mTicTacToe.reset();
        for (ImageView[] imagesRow: mWinningCircles) {
            for (ImageView image: imagesRow) {
                image.setImageDrawable(null);
                image.setEnabled(true);
            }
        }
        mBinding.winnerImage.setVisibility(View.INVISIBLE);
        mBinding.gameOverStatus.setVisibility(View.INVISIBLE);
        mUser = newUser();
        mBinding.turnTextLabel.setVisibility(View.VISIBLE);
        mBinding.turnImage.setVisibility(View.VISIBLE);
        mBinding.turnImage.setImageResource(mUser == 1 ? R.drawable.ic_tick : R.drawable.ic_cross);
    }

    @Override
    public void onGameTie() {
        mBinding.turnImage.setVisibility(View.GONE);
        mBinding.turnTextLabel.setVisibility(View.GONE);
        mBinding.winnerImage.setVisibility(View.INVISIBLE);
        mBinding.gameOverStatus.setVisibility(View.VISIBLE);
        mBinding.gameOverStatus.setText(R.string.tie);
        for (ImageView[] imagesRow: mWinningCircles) {
            for (ImageView image: imagesRow) {
                image.setImageResource(R.drawable.ic_win);
                image.setEnabled(false);
            }
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
}