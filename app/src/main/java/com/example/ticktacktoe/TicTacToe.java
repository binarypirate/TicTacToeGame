package com.example.ticktacktoe;

import com.example.ticktacktoe.MainActivity.WinningDiagonal;

public class TicTacToe {

    OnGameWinListener mListener;
    int mUser;

    int[][] ticTacToe = {
            {2,2,2},
            {2,2,2},
            {2,2,2}
    };

    public TicTacToe(OnGameWinListener listener) {
        mListener = listener;
    }

    public void setValue(int user, RowPosition rowPos, ColumnPosition colPos) {
        int row = rowPos == RowPosition.TOP ? 0 : rowPos == RowPosition.MID ? 1 : 2;
        int col = colPos == ColumnPosition.LEFT ? 0 : colPos == ColumnPosition.MID ? 1 : 2;
        ticTacToe[row][col] = mUser = user;
        check();
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
            mListener.onGameWin(mUser, WinningDiagonal.MID_HORIZONTAL);
        } else if (ticTacToe[0][2] == ticTacToe[1][2] && ticTacToe[1][2] == ticTacToe[2][2] && ticTacToe[2][2] != 2) {
            mListener.onGameWin(mUser, WinningDiagonal.END_VERTICAL);
        } else if (ticTacToe[0][0] == ticTacToe[1][1] && ticTacToe[1][1] == ticTacToe[2][2] && ticTacToe[2][2] != 2) {
            mListener.onGameWin(mUser, WinningDiagonal.TOP_START_TO_BOTTOM_END_AXIS);
        } else if (ticTacToe[0][2] == ticTacToe[1][1] && ticTacToe[1][1] == ticTacToe[2][0] && ticTacToe[2][0] != 2) {
            mListener.onGameWin(mUser, WinningDiagonal.TOP_END_TO_BOTTOM_START_AXIS);
        }
    }

    enum RowPosition {
        TOP, MID, BOTTOM
    }

    enum ColumnPosition {
        LEFT, MID, RIGHT
    }
}