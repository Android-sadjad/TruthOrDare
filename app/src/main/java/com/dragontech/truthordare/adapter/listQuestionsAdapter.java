package com.dragontech.truthordare.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dragontech.truthordare.R;
import com.dragontech.truthordare.classes.MyConstant;
import com.dragontech.truthordare.classes.MySharedPreferences;
import com.dragontech.truthordare.dialog.DeleteItemDialog;
import com.dragontech.truthordare.interfaces.CallBackMain;
import com.dragontech.truthordare.model.Questions;

import java.util.ArrayList;

public class listQuestionsAdapter extends RecyclerView.Adapter<listQuestionsAdapter.ViewHolder> {

    private Questions questions;
    private ArrayList<String> questionList;
    private String listName;
    private Animation fadeInAnimation;

    private int lastPosition = -1;

    public listQuestionsAdapter(ArrayList<String> questionList, String listName, Questions questions) {

        this.questionList = questionList;
        this.listName = listName;
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        fadeInAnimation = AnimationUtils.loadAnimation(parent.getContext(), R.anim.fade_in_animation);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.view_question, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull listQuestionsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        if (position > lastPosition) {
            holder.itemView.setAnimation(fadeInAnimation);
            lastPosition = position;
        }

        if (listName.equals(MyConstant.DARE) || listName.equals(MyConstant.TRUTH)) {
            holder.ivDeleteItem.setVisibility(View.GONE);
        }


        holder.ivDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DeleteItemDialog deleteItemDialog = new DeleteItemDialog(v.getContext(), new CallBackMain() {
                    @Override
                    public void callBack() {

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

                deleteItemDialog.show();
            }
        });

        holder.tvQuestion.setText(questionList.get(position));

        holder.tvIndexNumber.setText(String.valueOf(position + 1));

    }

    @Override
    public int getItemCount() {

        if (listName.equals(MyConstant.DARE) || listName.equals(MyConstant.TRUTH)) {

            return questions.getQuestionNumber();

        }

        return questionList.size();
    }

    @Override
    public void onViewDetachedFromWindow(listQuestionsAdapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvQuestion;
        TextView tvIndexNumber;
        ImageView ivDeleteItem;


        public ViewHolder(View itemView) {
            super(itemView);

            tvQuestion = itemView.findViewById(R.id.tv_question_item);
            tvIndexNumber = itemView.findViewById(R.id.tv_index_number);
            ivDeleteItem = itemView.findViewById(R.id.iv_delete_item);

        }
    }
}
