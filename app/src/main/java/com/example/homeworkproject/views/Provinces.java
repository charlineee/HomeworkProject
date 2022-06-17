package com.example.homeworkproject.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homeworkproject.R;

public class Provinces extends Fragment {
TextView province;
private String myProvince;

    public Provinces() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_provinces, container, false);

        province = v.findViewById(R.id.province);

        Bundle data = getArguments();
        if (data != null){
            myProvince = data.getString("key");
        }
        province.setText(myProvince);

        return v;
    }
}