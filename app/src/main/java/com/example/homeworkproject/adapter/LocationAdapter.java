package com.example.homeworkproject.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkproject.R;
import com.example.homeworkproject.model.Country;
import com.example.homeworkproject.views.Provinces;

import java.util.ArrayList;
import java.util.HashMap;


public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private ArrayList<Country> countryDataArrayList;
    private Context context;
    private HashMap<String, Integer> map = new HashMap<>();
    private Integer id = 0;
    private ItemClickListener clickListener;

    public LocationAdapter(ArrayList<Country> countryArrayList, Context context) {
        this.countryDataArrayList = countryArrayList; //from main
        this.context = context;
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
        Country model = countryDataArrayList.get(position);
        holder.countryText.setText(model.getCountryName());
        //add name as key and id as value in hashmap
        map.put(model.getCountryName(), model.getCountryId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick();
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

            countryText= itemView.findViewById(R.id.country);
            countryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //take country's name and use as key for hashmap
                //pull associated ID
                String tvText = countryText.getText().toString();
                id = map.get(tvText);

                //open fragment and pass bundle with country's ID

/*
                FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                Bundle data = new Bundle();
                data.putString("key", id.toString());
                provinces.setArguments(data);
                transaction.replace(R.id.recyclerView, provinces).addToBackStack(null).commit();*/
            }
        });
        }

    }
    public interface ItemClickListener {
        public void onItemClick();
    }
}
