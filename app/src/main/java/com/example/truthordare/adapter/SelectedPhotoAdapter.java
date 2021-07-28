package com.example.truthordare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.R;
import com.example.truthordare.model.Setting;

public class SelectedPhotoAdapter extends RecyclerView.Adapter<SelectedPhotoAdapter.ViewHolder>{

   boolean []lockFlags;

   int checkedPosition;
   Context context;
   Setting setting;

    public SelectedPhotoAdapter(Context context,Setting setting) {

        this.context=context;

        this.setting=setting;
        checkedPosition =setting.getPosition();
        lockFlags=setting.getLockFlags();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottle_item,parent,false));
    }

    @Override
    public void onBindViewHolder(SelectedPhotoAdapter.ViewHolder holder, int position) {


        holder.cbSelected.setChecked(checkedPosition == position);

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

                    checkedPosition=position;

                }

                else {

                    ((CheckBox) v).setChecked(true);
                    Toast.makeText(context, "حتما باید یک عکس رو انتخاب کنی", Toast.LENGTH_SHORT).show();

                }

                setting.setPosition(checkedPosition);
                notifyDataSetChanged();
            }
        });
        holder.ivLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lockFlags[position]=false;
                setting.setLockFlags(lockFlags);
                notifyDataSetChanged();

            }
        });


        int id = context.getResources().getIdentifier("bottle_" + (position+1), "drawable", context.getPackageName());

        holder.ivBottle.setBackgroundResource(id);





    }

    @Override
    public int getItemCount() {
        return lockFlags.length;
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
