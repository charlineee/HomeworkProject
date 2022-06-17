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


public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private ArrayList<Country> countryDataArrayList;
    Context context;

    public LocationAdapter(ArrayList<Country> countryArrayList, Context context) {
        this.countryDataArrayList = countryArrayList; //from main
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country model = countryDataArrayList.get(position);
        holder.countryText.setText(model.getCountryName());

    }

    @Override
    public int getItemCount() {
        return countryDataArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView countryText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            countryText= itemView.findViewById(R.id.country);
        }
    }
}
