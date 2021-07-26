package com.example.truthordare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.R;

import java.util.ArrayList;
import java.util.logging.Handler;

public class SelectedPhotoAdapter extends RecyclerView.Adapter<SelectedPhotoAdapter.ViewHolder>{

    boolean []lockFlags;
   boolean []checkBoxFlags;
   Context context;

    public SelectedPhotoAdapter(Context context) {

        this.context=context;
        checkBoxFlags=new boolean[getItemCount()];
        lockFlags=new boolean[getItemCount()];

        for (int i=0;i<checkBoxFlags.length;i++)
            if(i==0)
                checkBoxFlags[i]=true;
            else
            checkBoxFlags[i]=false;


            for (int i=0;i<lockFlags.length;i++)
                if(i==0||i==1||i==2)
                    lockFlags[i]=false;
                else
                    lockFlags[i]=true;



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottle_item,parent,false));
    }

    @Override
    public void onBindViewHolder(SelectedPhotoAdapter.ViewHolder holder, int position) {


        holder.cbSelected.setChecked(checkBoxFlags[position]);

        if(lockFlags[position]){
            holder.ivLock.setVisibility(View.VISIBLE);
            holder.cbSelected.setVisibility(View.GONE);
        }
        else{
            holder.ivLock.setVisibility(View.GONE);
            holder.cbSelected.setVisibility(View.VISIBLE);
        }

        holder.cbSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox) v).isChecked()){

                    for (int i=0;i<checkBoxFlags.length;i++)
                        if(i==position)
                            checkBoxFlags[i]=true;
                        else
                            checkBoxFlags[i]=false;
                }

                else {

                    ((CheckBox) v).setChecked(true);
                    Toast.makeText(context, "حتما باید یک عکس رو انتخاب کنی", Toast.LENGTH_SHORT).show();

                }
                notifyDataSetChanged();
            }
        });
        holder.ivLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lockFlags[position]=false;
                notifyDataSetChanged();

            }
        });







    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivBottle;
        ImageView ivLock;
        CheckBox cbSelected;

        public ViewHolder(View itemView) {
            super(itemView);

            ivBottle=itemView.findViewById(R.id.iv_bottle_selected);
            ivLock=itemView.findViewById(R.id.iv_lock);
            cbSelected=itemView.findViewById(R.id.cb_selected);
        }
    }
}
