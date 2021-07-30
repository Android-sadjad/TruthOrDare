package com.example.truthordare.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.classes.MySharedPreferences;

import java.util.ArrayList;

public class listQuestionsAdapter extends RecyclerView.Adapter<listQuestionsAdapter.ViewHolder> {

    ArrayList<String> questionList;
    String listName;

    public listQuestionsAdapter(ArrayList<String> questionList, String listName) {

        this.questionList = questionList;
        this.listName = listName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.question_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull listQuestionsAdapter.ViewHolder holder, int position) {

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
                        switch (listName) {


                            case MyConstant.TRUTH:
                                MySharedPreferences.getInstance(v.getContext()).getQuestions().setTruthQuestionList(questionList);
                                break;

                            case MyConstant.DARE:
                                MySharedPreferences.getInstance(v.getContext()).getQuestions().setDareQuestionList(questionList);
                                break;

                            case MyConstant.MY_TRUTH:
                                MySharedPreferences.getInstance(v.getContext()).getQuestions().setMyTruthQuestionList(questionList);

                                break;

                            case MyConstant.MY_DARE:
                                MySharedPreferences.getInstance(v.getContext()).getQuestions().setMyDareQuestionList(questionList);

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
            ivDeleteItem = itemView.findViewById(R.id.iv_delete_item);
        }
    }

}
