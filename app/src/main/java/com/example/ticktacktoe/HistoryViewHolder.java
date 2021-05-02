package com.example.ticktacktoe;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ticktacktoe.databinding.ItemPlayerWonBinding;

public class HistoryViewHolder extends RecyclerView.ViewHolder {
    ItemPlayerWonBinding binding;

    public HistoryViewHolder(@NonNull ItemPlayerWonBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
