package com.example.homeworkproject.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkproject.databinding.ProvinceLayoutBinding;
import com.example.homeworkproject.model.Province;

import java.util.ArrayList;


public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ViewHolder> {


    private ArrayList<Province> provinceDataArrayList;


    public ProvinceAdapter() {

    }

    public void addList(ArrayList<Province> provinceList){
        this.provinceDataArrayList = provinceList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProvinceAdapter.ViewHolder(ProvinceLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemView.province.setText(provinceDataArrayList.get(position).getProvinceName());

    }

    @Override
    public int getItemCount() {
        if (provinceDataArrayList == null){
            return 0;
        }
        return provinceDataArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ProvinceLayoutBinding itemView;

        public ViewHolder(@NonNull ProvinceLayoutBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

    }

}
