package com.example.locationvoiture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookCar extends AppCompatActivity {

    TextView brandTextView,modelTextView,priceTextView;
    ImageView imageview;

    Button book;
    Button Cancel;
    DatePicker pickupdate,returnddate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_page);
        book=findViewById(R.id.reserve_button);
        Cancel=findViewById(R.id.cancel_button);
        pickupdate=findViewById(R.id.pickupDate);
        returnddate=findViewById(R.id.returnDate);
        Intent intent = getIntent();
        if (intent != null) {
            String brand = intent.getStringExtra("KEY_BRAND");
            String model = intent.getStringExtra("KEY_MODEL");
            float price = intent.getFloatExtra("KEY_PRICE", 0.0f);
            String imageUrl = intent.getStringExtra("KEY_IMAGE_URL");

            brandTextView=findViewById(R.id.brandText);
             modelTextView = findViewById(R.id.modelText);
             priceTextView = findViewById(R.id.priceText);
            imageview = findViewById(R.id.Carimage);

            brandTextView.setText(brand);
            modelTextView.setText(model);
            priceTextView.setText(String.valueOf(price));


            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.caricon)
                    .into(imageview);
        }
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brand = brandTextView.getText().toString();
                String model = modelTextView.getText().toString();
                String price = priceTextView.getText().toString();
                int day = pickupdate.getDayOfMonth();
                int month = pickupdate.getMonth() + 1;
                int year = pickupdate.getYear();
                String pickupDate = year + "-" + month + "-" + day;
                int dayr = returnddate.getDayOfMonth();
                int monthr = returnddate.getMonth() + 1;
                int yearr = returnddate.getYear();
                String returnddate = yearr + "-" + monthr + "-" + dayr;

                Intent intent = new Intent(BookCar.this, ConfirmBook.class);


                intent.putExtra("KEY_BRAND", brand);
                intent.putExtra("KEY_MODEL", model);
                intent.putExtra("KEY_PRICE", price);
                intent.putExtra("KEY_PICKUP_DATE", pickupDate);
                intent.putExtra("KEY_RETURN_DATE", returnddate);


                startActivity(intent);
            }

        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookCar.this, Home.class);
                startActivity(intent);
            }
        });
    }
}

