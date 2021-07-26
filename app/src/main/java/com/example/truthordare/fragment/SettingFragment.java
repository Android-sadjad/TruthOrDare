package com.example.truthordare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.R;
import com.example.truthordare.adapter.SelectedPhotoAdapter;

public class SettingFragment extends Fragment {


    RecyclerView rvSelectPhoto;
    SelectedPhotoAdapter selectedPhotoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting,container,false);
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        init();



    }

    private void init() {
        selectedPhotoAdapter=new SelectedPhotoAdapter(getContext());
        rvSelectPhoto.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rvSelectPhoto.setAdapter(selectedPhotoAdapter);

    }

    private void findViews(View view) {

        rvSelectPhoto=view.findViewById(R.id.rv_select_photo);
    }
}
