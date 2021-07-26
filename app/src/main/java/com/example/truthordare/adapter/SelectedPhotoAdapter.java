package com.example.truthordare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.R;

public class SelectedPhotoAdapter extends RecyclerView.Adapter<SelectedPhotoAdapter.ViewHolder>{


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottle_item,parent,false));
    }

    @Override
    public void onBindViewHolder(SelectedPhotoAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivBottle;
        CheckBox cbSelected;

        public ViewHolder(View itemView) {
            super(itemView);

            ivBottle=itemView.findViewById(R.id.iv_bottle_selected);
            cbSelected=itemView.findViewById(R.id.cb_selected);
        }
    }
}
