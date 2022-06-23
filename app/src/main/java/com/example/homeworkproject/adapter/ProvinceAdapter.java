package com.example.homeworkproject.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkproject.R;
import com.example.homeworkproject.model.Province;

import java.util.ArrayList;


public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ViewHolder> {

    private ArrayList<Province> provinceDataArrayList;


    public ProvinceAdapter(ArrayList<Province> provinceArrayList) {
        this.provinceDataArrayList = provinceArrayList; //from main call

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.province_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.provinceText.setText(provinceDataArrayList.get(position).getProvinceName());

    }

    @Override
    public int getItemCount() {
        return provinceDataArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView provinceText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            provinceText = itemView.findViewById(R.id.province);
        }

    }

}
