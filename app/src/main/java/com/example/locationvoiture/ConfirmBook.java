package com.example.locationvoiture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfirmBook extends AppCompatActivity {

    Button book;
    Button back;
    TextView returnDateTextView;
    TextView pickupDateTextView;
    TextView priceTextView;
    TextView modelTextView;
    TextView brandTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmbook);
        book = findViewById(R.id.book_button);
        back=findViewById(R.id.back_button);
        Intent intent = getIntent();
        if (intent != null) {
            String brand = intent.getStringExtra("KEY_BRAND");
            String model = intent.getStringExtra("KEY_MODEL");
            String price = intent.getStringExtra("KEY_PRICE");
            String pickupDate = intent.getStringExtra("KEY_PICKUP_DATE");
            String returnDate = intent.getStringExtra("KEY_RETURN_DATE");


            brandTextView = findViewById(R.id.BrandText);
            modelTextView = findViewById(R.id.ModelText);
            priceTextView = findViewById(R.id.PriceText);
            pickupDateTextView = findViewById(R.id.PickupDate);
            returnDateTextView = findViewById(R.id.ReturnDate);

            brandTextView.setText("Brand: " + brand);
            modelTextView.setText("Model: " + model);
            priceTextView.setText("Price: " + price);
            pickupDateTextView.setText("Pickup Date: " + pickupDate);
            returnDateTextView.setText("Return Date: " + returnDate);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmBook.this, Home.class);
                startActivity(intent);

            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String brand = brandTextView.getText().toString();
                String model = modelTextView.getText().toString();
                String price = priceTextView.getText().toString();
                String pickupDate = pickupDateTextView.getText().toString();
                String returnDate = returnDateTextView.getText().toString();


                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    String userId = user.getUid();


                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference bookingsRef = database.getReference("bookings").child(userId);


                    String bookingKey = bookingsRef.push().getKey();


                    Booking booking = new Booking(bookingKey, brand, model, price, pickupDate, returnDate);


                    bookingsRef.child(bookingKey).setValue(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Booking successful
                                    Toast.makeText(ConfirmBook.this, "Booking Successful!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ConfirmBook.this, Home.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Booking failed
                                    Toast.makeText(ConfirmBook.this, "Booking Failed. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            });


                }

            }
        });
    }
}