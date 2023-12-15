package com.example.locationvoiture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.navbar,menu);
        MenuItem item1= menu.findItem(R.id.mybooks);
        MenuItem item2=menu.findItem(R.id.quit);
        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Use YourActivity.this to refer to the activity context
                Intent intent = new Intent(Home.this, MyBooks.class);
                startActivity(intent);
                return  true;
            }
        });
        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Use YourActivity.this to refer to the activity context
                finish();
                return true;
            }
        });

        return true;
    }
    RecyclerView recyclerview;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList <Car> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        recyclerview = findViewById(R.id.recyclerview);
        database = FirebaseDatabase.getInstance().getReference().child("Cars");
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        myAdapter = new MyAdapter(this, list, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Car car) {
                // Handle item click by opening another activity and passing data
                Intent intent = new Intent(Home.this, BookCar.class);
                intent.putExtra("KEY_BRAND", car.getBrand());
                intent.putExtra("KEY_MODEL", car.getModel());
                intent.putExtra("KEY_PRICE", car.getPrice());
                intent.putExtra("KEY_IMAGE_URL", car.getImageUrl());
                startActivity(intent);
            }
        });
        recyclerview.setAdapter(myAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Car car = dataSnapshot.getValue(Car.class);
                    list.add(car);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }}



