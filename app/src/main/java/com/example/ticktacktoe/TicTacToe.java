package com.example.ticktacktoe;

import com.example.ticktacktoe.MainActivity.WinningDiagonal;

import java.util.Arrays;

public class TicTacToe {

    OnGameEventListener mListener;
    int mUser;

    int[][] ticTacToe = {
            {2,2,2},
            {2,2,2},
            {2,2,2}
    };

    public TicTacToe(OnGameEventListener listener) {
        mListener = listener;
    }

    public void setValue(int user, RowPosition rowPos, ColumnPosition colPos) {
        int row = rowPos == RowPosition.TOP ? 0 : rowPos == RowPosition.MID ? 1 : 2;
        int col = colPos == ColumnPosition.LEFT ? 0 : colPos == ColumnPosition.MID ? 1 : 2;
        ticTacToe[row][col] = mUser = user;
        check();
    }

    public void reset() {
        for (int[] ints : ticTacToe) {
            Arrays.fill(ints, 2);
        }
    }

    public void checkTie() {
        boolean is2Exist = false;
        for (int[] row: ticTacToe) {
            for (int col: row) {
                if (col == 2) {
                    is2Exist = true;
                    break;
                }
            }
        }
        if (!is2Exist) {
            mListener.onGameTie();
        }
    }

    private void check() {
        if (ticTacToe[0][0] == ticTacToe[0][1] && ticTacToe[0][1] == ticTacToe[0][2] && ticTacToe[0][0] != 2) {
            mListener.onGameWin(mUser, WinningDiagonal.TOP_HORIZONTAL);
        } else if (ticTacToe[1][0] == ticTacToe[1][1] && ticTacToe[1][1] == ticTacToe[1][2] && ticTacToe[1][0] != 2) {
            mListener.onGameWin(mUser, WinningDiagonal.MID_HORIZONTAL);
        } else if (ticTacToe[2][0] == ticTacToe[2][1] && ticTacToe[2][1] == ticTacToe[2][2] && ticTacToe[2][0] != 2) {
            mListener.onGameWin(mUser, WinningDiagonal.BOTTOM_HORIZONTAL);
        } else if (ticTacToe[0][0] == ticTacToe[1][0] && ticTacToe[1][0] == ticTacToe[2][0] && ticTacToe[2][0] != 2) {
            mListener.onGameWin(mUser, WinningDiagonal.START_VERTICAL);
        } else if (ticTacToe[0][1] == ticTacToe[1][1] && ticTacToe[1][1] == ticTacToe[2][1] && ticTacToe[2][1] != 2) {
            mListener.onGameWin(mUser, WinningDiagonal.MID_VERTICAL);
        } else if (ticTacToe[0][2] == ticTacToe[1][2] && ticTacToe[1][2] == ticTacToe[2][2] && ticTacToe[2][2] != 2) {
            mListener.onGameWin(mUser, WinningDiagonal.END_VERTICAL);
        } else if (ticTacToe[0][0] == ticTacToe[1][1] && ticTacToe[1][1] == ticTacToe[2][2] && ticTacToe[2][2] != 2) {
            mListener.onGameWin(mUser, WinningDiagonal.TOP_START_TO_BOTTOM_END_AXIS);
        } else if (ticTacToe[0][2] == ticTacToe[1][1] && ticTacToe[1][1] == ticTacToe[2][0] && ticTacToe[2][0] != 2) {
            mListener.onGameWin(mUser, WinningDiagonal.TOP_END_TO_BOTTOM_START_AXIS);
        } else {
            checkTie();
        }
    }

    enum RowPosition {
        TOP, MID, BOTTOM
    }

    enum ColumnPosition {
        LEFT, MID, RIGHT
    }
}