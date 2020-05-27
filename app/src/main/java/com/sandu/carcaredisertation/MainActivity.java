package com.sandu.carcaredisertation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText reg_et;
    TextView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //         Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //.....//



        reg_et = findViewById(R.id.reg_et);
        search = findViewById(R.id.search_btn);






        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("tain",String.valueOf(reg_et.getText()));

                Intent i = new Intent(MainActivity.this, CarDetailScreen.class);
                i.putExtra("plate", String.valueOf(reg_et.getText()));

                startActivity(i);

            }
        });


    }
}
