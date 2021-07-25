package com.example.truthordare.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.R;
import com.example.truthordare.classes.MySharedPreferences;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    ArrayList<String> questionList;
    String listName;

    public RvAdapter(ArrayList<String> questionList,String listName) {

        this.questionList = questionList;
        this.listName=listName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.question_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder holder, int position) {

        holder.ivDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(v.getContext());

                deleteDialog.setTitle("حذف سوال");
                deleteDialog.setMessage("آیا میخواهید این سوال را حذف کنید؟");

                deleteDialog.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        questionList.remove(position);
                        switch (listName){

                            case "default_truth":
                                MySharedPreferences.getInstance(v.getContext()).putTruthQuestionList(questionList);
                                break;

                            case "default_dare":
                                MySharedPreferences.getInstance(v.getContext()).putDareQuestionList(questionList);
                                break;

                            case "my_truth":
                                MySharedPreferences.getInstance(v.getContext()).putMyTruthList(questionList);

                                break;

                            case "my_dare":
                                MySharedPreferences.getInstance(v.getContext()).putMyDareList(questionList);

                                break;

                        }
                        notifyDataSetChanged();
                    }


                });
                deleteDialog.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                deleteDialog.create().show();



            }
        });

        holder.setText(questionList.get(position));

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvQuestion;
        ImageView ivDeleteItem;

        public void setText(String text) {
            tvQuestion.setText(text);
        }


        public ViewHolder(View itemView) {
            super(itemView);

            tvQuestion = itemView.findViewById(R.id.tv_question_item);
            ivDeleteItem=itemView.findViewById(R.id.iv_delete_item);
        }
    }



}
