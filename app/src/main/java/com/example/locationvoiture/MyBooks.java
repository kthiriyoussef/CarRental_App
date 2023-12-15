package com.example.locationvoiture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyBooks extends AppCompatActivity {
    RecyclerView recyclerview;
    DatabaseReference database;
    ArrayList<Booking> list; // Assuming you have a Booking class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybooks);

        recyclerview = findViewById(R.id.recyclerView);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();
        database = FirebaseDatabase.getInstance().getReference().child("bookings").child(userId);

        list = new ArrayList<>();


        MyBooksAdapter adapter = new MyBooksAdapter(this, list, new MyBooksAdapter.OnItemClickListener() {

            @Override
            public void onDeleteClick(String bookingid) {
                deleteBooking(bookingid);
            }
        });


        recyclerview.setAdapter(adapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear(); // Clear the list before adding new data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Booking booking = snapshot.getValue(Booking.class);
                    list.add(booking);
                }
                adapter.notifyDataSetChanged(); // Notify the adapter that data has changed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors, if any
            }
        });
    }
    private void deleteBooking(String bookingid) {

        try {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser();
            if (user != null) {
                String userId = user.getUid();
                DatabaseReference bookingRef = database.child("bookings").child(userId).child(bookingid);

                Toast.makeText(MyBooks.this, "book id" + bookingid, Toast.LENGTH_SHORT).show();

                Task<Void> mTask = bookingRef.setValue(null);
                mTask.addOnCompleteListener(new OnCompleteListener<Void>()  {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MyBooks.this, "Book deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MyBooks.this, "Failed to delete book", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (Exception e) {
            Toast.makeText(MyBooks.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
}
}