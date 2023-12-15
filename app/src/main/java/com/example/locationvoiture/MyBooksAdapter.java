package com.example.locationvoiture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyBooksAdapter extends RecyclerView.Adapter<MyBooksAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Booking> list;
    private final OnItemClickListener listener;

    public MyBooksAdapter(Context context, ArrayList<Booking> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onDeleteClick(String bookingId);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.mainhome_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Booking book = list.get(position);
        holder.brand.setText(book.getBrand());
        holder.model.setText(book.getModel());
        holder.price.setText(String.valueOf(book.getPrice()));
        holder.pickupdate.setText(book.getPickupDate());
        holder.returndate.setText(book.getReturnDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView brand, model, price, pickupdate, returndate;
        ImageView img;
        Button delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            brand = itemView.findViewById(R.id.brandnametext);
            model = itemView.findViewById(R.id.modelnametext);
            price = itemView.findViewById(R.id.price);
            img = itemView.findViewById(R.id.img1);
            pickupdate = itemView.findViewById(R.id.pickupDate);
            returndate = itemView.findViewById(R.id.returnDate);
            delete = itemView.findViewById(R.id.delete_button);

            delete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Booking currentBooking = list.get(position);

                    if (listener != null) {
                        listener.onDeleteClick(String.valueOf(currentBooking.getBookingid()));
                    }
                }
            });
        }
    }
}

