package com.example.ticktacktoe;

import com.example.ticktacktoe.MainActivity.WinningDiagonal;

public interface OnGameWinListener {
    void onGameWin(int winner, WinningDiagonal diagonal);
}
