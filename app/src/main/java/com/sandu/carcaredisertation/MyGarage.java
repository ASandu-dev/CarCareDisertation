package com.sandu.carcaredisertation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandu.carcaredisertation.PojoAndDatabase.VehRepository;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleData;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleImage;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleMot;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleTax;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyGarage extends AppCompatActivity implements MyGarageAdapter.OnCarClickListener {



    RecyclerView recyclerView;
    Button back_btn;

    MyGarageAdapter adapter;

    private List<VehicleTax> tax = new ArrayList<>();
    private List<VehicleMot> mot = new ArrayList<>();
    private List<VehicleData> cars = new ArrayList<>();
    private List<VehicleImage> image = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_garage);



        back_btn = findViewById(R.id.back_btn);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        adapter = new MyGarageAdapter(cars, image, this);
        recyclerView.setAdapter(adapter);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyGarage.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                MyGarage.this.finish();
            }
        });


        VehRepository vehRepository = new VehRepository(getApplication());
        vehRepository.getAllVehicles().observe(this, new Observer<List<VehicleData>>() {
            @Override
            public void onChanged(List<VehicleData> vehicleData) {

                adapter.setCars(vehicleData);

            }
        });
        vehRepository.getAllImages().observe(this, new Observer<List<VehicleImage>>() {
            @Override
            public void onChanged(List<VehicleImage> vehicleImages) {

                adapter.setImages(vehicleImages);
            }
        });



    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

            //Remove swiped item from list and notify the RecyclerView
            final int position = viewHolder.getAdapterPosition();
            VehicleData vehicleData = adapter.getVehicleAtPosition(position);
            VehicleImage vehicleImage = adapter.getImageAtPosition(position);

            VehicleViewModel vehicleViewModel = new VehicleViewModel(adapter.application);
            vehicleViewModel.deleteCar(vehicleData);
            vehicleViewModel.deleteImage(vehicleImage);
        }
    };

    @Override
    public void onCarClick(int position) {

        VehicleData vehicleData = adapter.getVehicleAtPosition(position);
        VehicleImage vehicleImage = adapter.getImageAtPosition(position);


        Intent i = new Intent(this, CarDisplay.class);
        i.putExtra("Make", vehicleData.getVehMake());
        i.putExtra("Model", vehicleData.getVehModel());
        i.putExtra("Year", vehicleData.getVehYear());
        i.putExtra("First_Registered", vehicleData.getVehFirstRegistered());
        i.putExtra("Colour", vehicleData.getVehColour());
        i.putExtra("Fuel_Type", vehicleData.getVehFuelType());
        i.putExtra("Cylinder_Capacity", vehicleData.getVehCylinderCapacity());
        i.putExtra("CO2_Emissions", vehicleData.getVehCo2Emissions());
        i.putExtra("Max_Power", vehicleData.getVehMaxPower());
        i.putExtra("Max_Torque", vehicleData.getVehMaxTorque());
        i.putExtra("Acceleration", vehicleData.getVehAcceleration());
        i.putExtra("Top_Speed", vehicleData.getVehTopSpeed());
        i.putExtra("Urban", vehicleData.getVehUrban());
        i.putExtra("Extra_Urban", vehicleData.getVehExtraUrban());
        i.putExtra("Combined", vehicleData.getVehCombined());
        i.putExtra("Transmission", vehicleData.getVehTransmission());
        i.putExtra("Gears", vehicleData.getVehGears());
        i.putExtra("Drive_Train", vehicleData.getVehDriveTrain());
        i.putExtra("No_of_Cylinders", vehicleData.getVehCylinders());
        i.putExtra("engine_Capacity", vehicleData.getVehLitres());
        i.putExtra("No_of_Doors", vehicleData.getVehDoors());
        i.putExtra("No_of_Seats", vehicleData.getVehSeats());
        i.putExtra("Body_Style", vehicleData.getVehBodyStyle());
        i.putExtra("Height", vehicleData.getVehHeight());
        i.putExtra("Width", vehicleData.getVehWidth());
        i.putExtra("Length", vehicleData.getVehLength());
        i.putExtra("Wheelbase", vehicleData.getVehWheelBase());
        i.putExtra("VehicleImage", vehicleImage.getVehImg());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        MyGarage.this.finish();
    }

    //        Intent i = new Intent(this, CarDetailScreen.class);
//        VehicleData clickedItem = vData.get(position);
//        i.putExtra("vehicle_make", clickedItem.getVehMake());
//        i.putExtra("vehicle_model", clickedItem.getVehModel());
//        i.putExtra("vehicle_consumption", clickedItem.getMpg());
//        i.putExtra("vehicle_horsepower", clickedItem.getBhp());
//        i.putExtra("vehicle_width", clickedItem.getVehWidth());
//        i.putExtra("vehicle_height", clickedItem.getVehHeight());
//        i.putExtra("vehicle_length", clickedItem.getVehLength());
//        i.putExtra("vehicle_engine", clickedItem.getVehCylinders());
//        VehicleImage clickedItemImage = vehicleImages.get(position);
//        i.putExtra("vehicle_image", clickedItemImage.getVehImg());
//        startActivity(i);
}