package com.sandu.carcaredisertation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    EditText reg_et;
    TextView search;
    Button garage_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //         Full Screen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        //.....//


        reg_et = findViewById(R.id.reg_et);
        search = findViewById(R.id.search_btn);
        garage_btn = findViewById(R.id.myGarage_btn);


        //Listeners
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("tain", String.valueOf(reg_et.getText()));

                Intent i = new Intent(MainActivity.this, CarDetailScreen.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("plate", String.valueOf(reg_et.getText()));

                startActivity(i);

            }
        });


        garage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MyGarage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("plate", String.valueOf(reg_et.getText()));

                startActivity(i);
                MainActivity.this.finish();
            }
        });

    }
}
