package com.example.homeworkproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkproject.R;
import com.example.homeworkproject.model.Country;

import java.util.ArrayList;
import java.util.HashMap;


public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private ArrayList<Country> countryDataArrayList;
    private HashMap<String, Integer> map = new HashMap<>();
    private Integer id = 0;
    private ItemClickListener clickListener;

    public LocationAdapter(ArrayList<Country> countryArrayList,ItemClickListener clickListener) {
        this.countryDataArrayList = countryArrayList; //from main

        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.countryText.setText(countryDataArrayList.get(position).getCountryName());
        holder.countryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(countryDataArrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryDataArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView countryText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            countryText = itemView.findViewById(R.id.country);

        }

    }
    public interface ItemClickListener {
        public void onItemClick(Country countryDataArrayList);
    }

}
