package com.example.ticktacktoe;

import com.example.ticktacktoe.MainActivity.WinningDiagonal;

public interface OnGameEventListener {
    void onGameWin(int winner, WinningDiagonal diagonal);
    void onGameTie();
}
