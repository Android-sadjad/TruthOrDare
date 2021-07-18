package com.example.truthordare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuestionListFragment extends Fragment {

    QuestionFragment truthQuestionFragment;
    QuestionFragment dareQuestionFragment;
    Button btnTruth;
    Button btnDare;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_question_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        init();
        configuration();

        loadFragment(truthQuestionFragment);

    }

    private void findViews(View view){

        btnDare=view.findViewById(R.id.btn_dare_question_list);
        btnTruth=view.findViewById(R.id.btn_truth_question_list);


    }

    private void init(){

        truthQuestionFragment = new QuestionFragment();
        dareQuestionFragment = new QuestionFragment();

    }

    private void configuration(){

        btnTruth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(truthQuestionFragment);
            }
        });

        btnDare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(dareQuestionFragment);
            }
        });


    }

    public void loadFragment(Fragment fragment) {

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_question_container, fragment).commit();

    }
}
