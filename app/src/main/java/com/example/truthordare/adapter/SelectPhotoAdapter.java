package com.example.truthordare.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.dialog.AdvertisingSelectDialog;
import com.example.truthordare.interfaces.CallBackUpdateSelect;
import com.example.truthordare.model.Setting;

public class SelectPhotoAdapter extends RecyclerView.Adapter<SelectPhotoAdapter.ViewHolder> {

    boolean[] lockFlags;
    int checkedPosition;

    Activity activity;
    Setting setting;

    public SelectPhotoAdapter(Activity activity, Setting setting) {

        this.activity = activity;
        this.setting = setting;

        checkedPosition = setting.getPosition();
        lockFlags = setting.getLockFlags();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_bottle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SelectPhotoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.cbSelected.setChecked(checkedPosition == position);

        int id = activity.getResources().getIdentifier("bottle_" + (position + 1), "drawable", activity.getPackageName());
        holder.ivBottle.setImageResource(id);

        if (lockFlags[position]) {
            holder.ivLock.setVisibility(View.VISIBLE);
            holder.cbSelected.setVisibility(View.INVISIBLE);
        } else {

            holder.ivLock.setVisibility(View.GONE);
            holder.cbSelected.setVisibility(View.VISIBLE);
        }



        holder.clItemBottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lockFlags[position]){
                    if(MyConstant.isNetworkAvailable(activity)){

                        AdvertisingSelectDialog advertisingSelectDialog=new AdvertisingSelectDialog(activity, setting,new CallBackUpdateSelect() {
                            @Override
                            public void updateSelect() {

                                Toast.makeText(activity, R.string.added_photo, Toast.LENGTH_SHORT).show();

                                lockFlags[position] = false;
                                setting.setLockFlags(lockFlags);
                                setting.updateSetting(activity,setting);

                                notifyDataSetChanged();


                            }

                        });
                        advertisingSelectDialog.show();


                    }

                }
                else {


                   checkedPosition=position;
                    setting.setPosition(checkedPosition);
                    notifyDataSetChanged();


                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return lockFlags.length;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout clItemBottle;
        ImageView ivBottle;
        ImageView ivLock;
        CheckBox cbSelected;

        public ViewHolder(View itemView) {
            super(itemView);

            clItemBottle=itemView.findViewById(R.id.cl_item_bottle);
            ivBottle = itemView.findViewById(R.id.iv_bottle_selected);
            ivLock = itemView.findViewById(R.id.iv_lock);
            cbSelected = itemView.findViewById(R.id.cb_selected);
        }
    }
}
