package com.example.homeworkproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homeworkproject.R;
import com.example.homeworkproject.model.Country;

import java.util.ArrayList;
import java.util.Locale;


public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    public ArrayList<Country> countryDataArrayList;
    private final ItemClickListener clickListener;
    Context context;

    public LocationAdapter(ArrayList<Country> countryArrayList, Context context, ItemClickListener clickListener) {
        this.countryDataArrayList = countryArrayList; //from main call
        this.context = context;
        this.clickListener = clickListener;
    }

    public void addList(ArrayList<Country> countryList){
        this.countryDataArrayList = countryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_layout, parent, false);
        Log.d("TAG", "onCreateViewHolder: ");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.countryText.setText(countryDataArrayList.get(position).getCountryName());
        //load flag images
        Glide.with(context)
                .load("https://raw.githubusercontent.com/hampusborgos/country-flags/main/png250px/" + countryDataArrayList.get(position).getCountryCode().toLowerCase(Locale.ROOT)+".png")//url
                .circleCrop()
                .into(holder.flags);
        //click listener for item selection
        holder.countryText.setOnClickListener(view -> clickListener.onItemClick(countryDataArrayList.get(position)));
    }

    @Override
    public int getItemCount() {
        if (countryDataArrayList == null){
            return 0;
        }
        return countryDataArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView countryText;
        private ImageView flags;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            countryText = itemView.findViewById(R.id.country);
            flags = itemView.findViewById(R.id.flag);
        }

    }
    public interface ItemClickListener {
        void onItemClick(Country countryDataArrayList);
    }

}
