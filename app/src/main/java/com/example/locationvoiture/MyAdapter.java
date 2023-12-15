package com.example.locationvoiture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Car> list;
    private OnItemClickListener listener;

    public MyAdapter(Context context, ArrayList<Car> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Car car);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.main_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Car car = list.get(position);
        holder.brand.setText(car.getBrand());
        holder.model.setText(car.getModel());
        holder.price.setText(String.valueOf(car.getPrice()));
        Glide.with(context)
                .load(car.getImageUrl())
                .placeholder(R.drawable.caricon)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView brand, model, price;
        ImageView img;
        Button reserve;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            brand = itemView.findViewById(R.id.brandnametext);
            model = itemView.findViewById(R.id.modelnametext);
            price = itemView.findViewById(R.id.price);
            img = itemView.findViewById(R.id.img1);
            reserve = itemView.findViewById(R.id.delete_button);

            reserve.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(list.get(position));
                }
            });
        }
    }
}
