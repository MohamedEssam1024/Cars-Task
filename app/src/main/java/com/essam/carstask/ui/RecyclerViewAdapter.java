package com.essam.carstask.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.essam.carstask.R;
import com.essam.carstask.data.api.apiResponseDataClasses.CarsList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<CarsList> carsList = new ArrayList();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    public void insertData(List<CarsList> newCars){
        carsList.addAll(newCars);
        notifyDataSetChanged();
    }

    public void clear() {
        carsList = new ArrayList();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CarsList cars = carsList.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;
        viewHolder.brand.setText(cars.getBrand());
        viewHolder.isUsed.setText(cars.isUsed());
        viewHolder.constructionYear.setText(cars.getConstractionYear());
        Picasso.get().load(cars.getImageUrl()).into(viewHolder.carImage);
    }

    @Override
    public int getItemCount() {
        return carsList.size();
    }

    private class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        TextView brand;
        TextView isUsed;
        TextView constructionYear;
        ImageView carImage;

        public RecyclerViewViewHolder(View rootView) {
            super(rootView);

            brand = rootView.findViewById(R.id.brand);
            isUsed = rootView.findViewById(R.id.isUsed);
            constructionYear = itemView.findViewById(R.id.constructionYear);
            carImage = rootView.findViewById(R.id.carImage);
        }
    }
}
