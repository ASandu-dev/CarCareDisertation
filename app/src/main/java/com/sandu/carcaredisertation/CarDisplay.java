package com.sandu.carcaredisertation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarDisplay extends AppCompatActivity {

    private ImageView imageView;
    private TextView model_make;
    private Button back_btn;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

    public List<String> basicInfo = new ArrayList<>();
    public List<String> performance = new ArrayList<>();
    public List<String> economy = new ArrayList<>();
    public List<String> engineAndGearbox = new ArrayList<>();
    public List<String> chassis = new ArrayList<>();
    public List<String> dimensions = new ArrayList<>();


    public String carImage, expDate, nextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_display);

        listView = findViewById(R.id.eListView);
        imageView = findViewById(R.id.car_img_holder2);
        model_make = findViewById(R.id.modelAndMake);
        back_btn = findViewById(R.id.button);


        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("VehicleImage");
        String make = intent.getStringExtra("Make");
        String model = intent.getStringExtra("Model");
        String year = intent.getStringExtra("Year");
        String first_registered = intent.getStringExtra("First_Registered");
        String colour = intent.getStringExtra("Colour");
        String fuel_type = intent.getStringExtra("Fuel_Type");
        String cylinder_capacity = intent.getStringExtra("Cylinder_Capacity");
        String co2_emissions = intent.getStringExtra("CO2_Emissions");
        String max_power = intent.getStringExtra("Max_Power");
        String max_torque = intent.getStringExtra("Max_Torque");
        String acceleration = intent.getStringExtra("Acceleration");
        String top_speed = intent.getStringExtra("Top_Speed");
        String urban = intent.getStringExtra("Urban");
        String extra_urban = intent.getStringExtra("Extra_Urban");
        String combined = intent.getStringExtra("Combined");
        String transmission = intent.getStringExtra("Transmission");
        String gears = intent.getStringExtra("Gears");
        String drive_train = intent.getStringExtra("Drive_Train");
        String no_of_cylinders = intent.getStringExtra("No_of_Cylinders");
        String engine_capacity = intent.getStringExtra("engine_Capacity");
        String no_of_doors = intent.getStringExtra("No_of_Doors");
        String no_of_seats = intent.getStringExtra("No_of_Seats");
        String body_style = intent.getStringExtra("Body_Style");
        String height = intent.getStringExtra("Height");
        String width = intent.getStringExtra("Width");
        String length = intent.getStringExtra("Length");
        String wheelbase = intent.getStringExtra("Wheelbase");


        headerList();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);

        basicInfo.add("Make: "+ make);
        basicInfo.add("Model: "+model);
        basicInfo.add("Year: "+year);
        basicInfo.add("First Registered: "+first_registered);
        basicInfo.add("Colour: "+colour);
        basicInfo.add("Fuel Type: "+fuel_type);
        basicInfo.add("Cylinder Capacity: "+cylinder_capacity);
        basicInfo.add("CO2 Emissions: "+co2_emissions);
        performance.add("Max Power (BHP): "+max_power);
        performance.add("Max Torque (Nm): "+max_torque);
        performance.add("0-60 Mph: "+acceleration);
        performance.add("Top Speed (Mph): "+top_speed);
        economy.add("Urban (Mpg): "+urban);
        economy.add("Extra-Urban (Mpg): "+extra_urban);
        economy.add("Combined (Mpg): "+combined);
        engineAndGearbox.add("Transmission: "+transmission);
        engineAndGearbox.add("Gears:"+gears);
        engineAndGearbox.add("Drive Train: "+drive_train);
        engineAndGearbox.add("No of Cylinders: "+no_of_cylinders);
        engineAndGearbox.add("Engine Capacity: "+engine_capacity);
        chassis.add("No of Doors: "+no_of_doors);
        chassis.add("No of Seats: "+no_of_seats);
        chassis.add("Body Style: "+body_style);
        dimensions.add("Height: "+height);
        dimensions.add("Width: "+width);
        dimensions.add("Length: "+length);
        dimensions.add("Wheelbase: "+wheelbase);

        Picasso.get().load(imageUrl).into(imageView);
        model_make.setText(String.format("%s - %s", model, make));

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CarDisplay.this, MyGarage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                CarDisplay.this.finish();
            }
        });
    }
    private void headerList() {
        listDataHeader = new ArrayList<>();

        listDataHeader.add("Basic Info");
        listDataHeader.add("Performance");
        listDataHeader.add("Economy");
        listDataHeader.add("Engine & Gearbox");
        listDataHeader.add("Chassis");
        listDataHeader.add("Dimensions");

        listHash = new HashMap<>();

//
        listHash.put("Basic Info", basicInfo);

        listHash.put("Performance", performance);

        listHash.put("Economy", economy);

        listHash.put("Engine & Gearbox", engineAndGearbox);

        listHash.put("Chassis", chassis);

        listHash.put("Dimensions", dimensions);

    }
}