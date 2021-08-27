package com.example.truthordare.adapter;

import android.annotation.SuppressLint;
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
import com.example.truthordare.model.Questions;

import java.util.ArrayList;

public class listQuestionsAdapter extends RecyclerView.Adapter<listQuestionsAdapter.ViewHolder> {

    Questions questions;
    ArrayList<String> questionList;
    String listName;

    public listQuestionsAdapter(ArrayList<String> questionList, String listName,Questions questions) {

        this.questionList = questionList;
        this.listName = listName;
        this.questions=questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.question_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull listQuestionsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (listName.equals(MyConstant.DARE) || listName.equals(MyConstant.TRUTH)){
            holder.ivDeleteItem.setVisibility(View.GONE);
        }



        holder.ivDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(v.getContext());

                deleteDialog.setTitle(R.string.delete_question);
                deleteDialog.setMessage(R.string.delete_message);
                deleteDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
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
                deleteDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                deleteDialog.create().show();


            }
        });

        holder.tvQuestion.setText(questionList.get(position));
        holder.tvIndexNumber.setText(String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {

        if (listName.equals(MyConstant.DARE) || listName.equals(MyConstant.TRUTH)){

            return  questions.getQuestionNumber();

        }

        return questionList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvQuestion;
        TextView tvIndexNumber;
        TextView tvNo;
        TextView tvYes;
        ImageView ivDeleteItem;


        public ViewHolder(View itemView) {
            super(itemView);

            tvQuestion = itemView.findViewById(R.id.tv_question_item);
            tvIndexNumber=itemView.findViewById(R.id.tv_index_number);
            ivDeleteItem = itemView.findViewById(R.id.iv_delete_item);

        }
    }

}
