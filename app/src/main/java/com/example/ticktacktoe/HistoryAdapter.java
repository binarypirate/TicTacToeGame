package com.example.ticktacktoe;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticktacktoe.databinding.ItemPlayerWonBinding;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    final List<GameOver> gameOverList;

    public HistoryAdapter(List<GameOver> gameOverList) {
        this.gameOverList = gameOverList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(ItemPlayerWonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.binding.winnerImage.setImageResource(gameOverList.get(position).status.equals("1") ? R.drawable.ic_tick : R.drawable.ic_cross);
    }

    @Override
    public int getItemCount() {
        return gameOverList.size();
    }

}
