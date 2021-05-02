package com.example.ticktacktoe;

import androidx.annotation.NonNull;

public class GameOver {
    public final String status;
    public final String footSteps;

    public GameOver(String status, String footSteps) {
        this.status = status;
        this.footSteps = footSteps;
    }

    @NonNull
    @Override
    public String toString() {
        return "GameOver{" +
                "status='" + status + '\'' +
                ", footSteps='" + footSteps + '\'' +
                '}';
    }
}
