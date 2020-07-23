package com.sandu.carcaredisertation;

import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sandu.carcaredisertation.PojoAndDatabase.DocId;
import com.sandu.carcaredisertation.PojoAndDatabase.VehRepository;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleData;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleDatabase;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleImage;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleMot;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleTax;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CarDetailScreen extends AppCompatActivity {


    Dialog popup;
    TextView mAm_tv, taxed_tv, mot_tv, popup_tax_tv, popup_mot_tv;
    ImageView imageView, close_popup_tax, close_popup_mot;
    Button newSearch, saveToGarage, info_tax, info_mot;

    private VehicleData vehicleData;
    private VehicleDatabase vehicleDatabase;
    Context context;

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
    public List<String> taxAndMot = new ArrayList<>();
    //Public Values Basic Info
    public String make, model, year, firstRegistered, colour, fuelType, cylinderCapacity, co2Emissions;
    //Public Values Performance
    public String maxPower, notToMax, topSpeed, maxTorque;
    //public values Economy
    public String urban, extraUrban, combined;
    //public values Engine and Gearbox
    public String transmission, gears, driveTrain, noCylinders, litres;
    //public Values Chassis
    public String doors, seats, bodyStyle;
    //Public Values Dimensions
    public String height, width, length, wheelbase;
    //Public Values Tax and MOT
    public String taxDue, motDue;
    //Public Values DocId
    public String documentId;

    public String carImage;

    public boolean isTaxed, validMot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardetail_activity);

        popup = new Dialog(this);

        context = this.getApplicationContext();

        //TextViews
        mAm_tv = findViewById(R.id.modelAndMake);
        taxed_tv = findViewById(R.id.taxed_tv);
        mot_tv = findViewById(R.id.mot_tv);
        //Image Views
        imageView = findViewById(R.id.car_img_holder);
        //Buttons
        saveToGarage = findViewById(R.id.saveToGarage);
        newSearch = findViewById(R.id.newSearch);
        info_tax = findViewById(R.id.info_tax);
        info_mot = findViewById(R.id.info_mot);

        listView = findViewById(R.id.eListView);



//        //Full Screen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        jsonParse();
        headerList();


        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);


        saveToGarage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                saveDataToDB();
                Intent i = new Intent(CarDetailScreen.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                Toast.makeText(CarDetailScreen.this, "Saved to My Garage", Toast.LENGTH_SHORT).show();
            }
        });

        newSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CarDetailScreen.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        info_tax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTaxPopup();
            }
        });
        info_mot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMotPopup();
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


    private void jsonParse() {

        Intent intent = getIntent();
        String plate = intent.getStringExtra("plate");
        Log.e("test", String.valueOf(plate));
        String urlTax = "https://uk1.ukvehicledata.co.uk/api/datapackage/VehicleTaxData?v=2&api_nullitems=1&auth_apikey=b5d7757f-da24-47e5-8cec-03caa133cf6c&user_tag=&key_VRM=" + plate;
        String urlMot = "https://uk1.ukvehicledata.co.uk/api/datapackage/MotHistoryData?v=2&api_nullitems=1&auth_apikey=b5d7757f-da24-47e5-8cec-03caa133cf6c&user_tag=&key_VRM=" + plate;
        String urlVehData = "https://uk1.ukvehicledata.co.uk/api/datapackage/VehicleData?v=2&api_nullitems=1&auth_apikey=b5d7757f-da24-47e5-8cec-03caa133cf6c&user_tag=&key_VRM=" + plate;
        String urlVehImage = "https://uk1.ukvehicledata.co.uk/api/datapackage/VehicleImageData?v=2&api_nullitems=1&auth_apikey=b5d7757f-da24-47e5-8cec-03caa133cf6c&user_tag=&key_VRM=" + plate;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait, while we load the data");
        progressDialog.show();

        //Get Tax Data
        StringRequest requestTax = new StringRequest(urlTax, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject responses = jsonObject.getJSONObject("Response");

                    JSONObject dataItems = responses.getJSONObject("DataItems");

                    JSONObject vehicleStatus = dataItems.getJSONObject("VehicleStatus");
                    JSONObject motVed = vehicleStatus.getJSONObject("MotVed");

                    //Extract Values
                    boolean taxedResponse = motVed.getBoolean("VedCurrentlyValid");
                    String expiryDate = motVed.getString("VedExpiryDate");


                    useTaxData(taxedResponse, expiryDate);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue requestQueueTax = Volley.newRequestQueue(getApplicationContext());
        requestQueueTax.add(requestTax);

        //Get Mot Data
        StringRequest requestMot = new StringRequest(urlMot, new com.android.volley.Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject responses = jsonObject.getJSONObject("Response");

                    JSONObject dataItems = responses.getJSONObject("DataItems");

                    JSONObject vehStatus = dataItems.getJSONObject("VehicleStatus");


                    boolean hasMotResponse = vehStatus.getBoolean("VehicleHasCurrentMot");
                    String nextDueDate = vehStatus.getString("NextMotDueDate");


                    Log.e("motdata", String.valueOf(hasMotResponse));


                    useMotData(hasMotResponse, nextDueDate);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue requestQueueMot = Volley.newRequestQueue(getApplicationContext());
        requestQueueMot.add(requestMot);

        //Get Vehicle Image
        StringRequest requestVehicleData = new StringRequest(urlVehData, new com.android.volley.Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject responses = jsonObject.getJSONObject("Response");

                    JSONObject dataItems = responses.getJSONObject("DataItems");

                    //Get Basic Info Details
                    JSONObject vehicleReg = dataItems.getJSONObject("VehicleRegistration");

                    String vehMake = vehicleReg.getString("Make");
                    String vehModel = vehicleReg.getString("Model");
                    String vehYear = vehicleReg.getString("YearOfManufacture");
                    String vehFirstRegistered = vehicleReg.getString("DateFirstRegisteredUk");
                    String vehColour = vehicleReg.getString("Colour");
                    String vehFuelType = vehicleReg.getString("FuelType");
                    String vehCylinderCapacity = vehicleReg.getString("EngineCapacity");
                    String vehCo2Emissions = vehicleReg.getString("Co2Emissions");

                    //Get Performance Info
                    JSONObject technicalDetails = dataItems.getJSONObject("TechnicalDetails");
                    JSONObject performance = technicalDetails.getJSONObject("Performance");
                    JSONObject torque = performance.getJSONObject("Torque");
                    JSONObject power = performance.getJSONObject("Power");
                    JSONObject maxSpeed = performance.getJSONObject("MaxSpeed");
                    JSONObject acceleration = performance.getJSONObject("Acceleration");

                    String vehMaxPower = power.getString("Bhp");
                    String vehAcceleration = acceleration.getString("ZeroTo60Mph");
                    String vehTopSpeed = maxSpeed.getString("Mph");
                    String vehMaxTorque = torque.getString("Nm");

                    //Get Economy Info
                    JSONObject economy = technicalDetails.getJSONObject("Consumption");
                    JSONObject urban = economy.getJSONObject("UrbanCold");
                    JSONObject extraUrban = economy.getJSONObject("ExtraUrban");
                    JSONObject combined = economy.getJSONObject("Combined");
                    String vehUrban = urban.getString("Mpg");
                    String vehExtraUrban = extraUrban.getString("Mpg");
                    String vehCombined = combined.getString("Mpg");

                    //Get Engine & Gearbox Info
                    JSONObject smt = dataItems.getJSONObject("SmmtDetails");
                    String vehTransmission = smt.getString("Transmission");
                    String vehGears = smt.getString("NumberOfGears");
                    String vehDriveTrain = smt.getString("DriveType");
                    String vehLitres = smt.getString("NominalEngineCapacity");
                    JSONObject technicalinfo = dataItems.getJSONObject("TechnicalDetails");
                    JSONObject general = technicalinfo.getJSONObject("General");
                    JSONObject engine = general.getJSONObject("Engine");
                    String vehCylinders = engine.getString("NumberOfCylinders");

                    //Get Chassis Info

                    String vehDoors = smt.getString("NumberOfDoors");
                    String vehBodyStyle = smt.getString("BodyStyle");
                    String vehSeats = vehicleReg.getString("SeatingCapacity");

                    //Get Dimensions Info
                    JSONObject dimensions = technicalDetails.getJSONObject("Dimensions");
                    String vehHeight = dimensions.getString("Height");
                    String vehWidth = dimensions.getString("Width");
                    String vehLength = dimensions.getString("CarLength");
                    String vehWheelBase = dimensions.getString("WheelBase");

                    //Get


                    useVehicleData(vehMake, vehModel, vehYear, vehFirstRegistered,
                            vehColour, vehFuelType, vehCylinderCapacity, vehCo2Emissions,
                            vehMaxPower, vehAcceleration, vehTopSpeed, vehMaxTorque, vehUrban,
                            vehExtraUrban, vehCombined, vehTransmission, vehGears, vehDriveTrain,
                            vehLitres, vehCylinders, vehDoors, vehBodyStyle, vehSeats, vehHeight,
                            vehWidth, vehLength, vehWheelBase);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue requestVehicleDataQueue = Volley.newRequestQueue(getApplicationContext());
        requestVehicleDataQueue.add(requestVehicleData);

        //GetVehicle Image
        StringRequest requestVehicleImg = new StringRequest(urlVehImage, new com.android.volley.Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject responses = jsonObject.getJSONObject("Response");

                    JSONObject dataItems = responses.getJSONObject("DataItems");

                    JSONObject vehImages = dataItems.getJSONObject("VehicleImages");
                    JSONArray imageDetailList = vehImages.getJSONArray("ImageDetailsList");

                    for (int i = 0; i < imageDetailList.length(); i++) {
                        JSONObject obj = imageDetailList.getJSONObject(i);
                        String vehImg = obj.getString("ImageUrl");

                        useVehicleImage(vehImg);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue requestVehImgQueue = Volley.newRequestQueue(getApplicationContext());
        requestVehImgQueue.add(requestVehicleImg);
        

    }

    public void useTaxData(boolean taxedResponse, String expiryDate) {
        isTaxed = taxedResponse;
        taxDue = expiryDate;


        if (isTaxed = true) {
            taxed_tv.setTextColor(Color.GREEN);
        } else {
            taxed_tv.setTextColor(Color.RED);
        }

    }

    public void useMotData(boolean hasMotResponse, String nextDueDate) {

        validMot = hasMotResponse;
        motDue = nextDueDate;


        if (validMot = true) {
            mot_tv.setTextColor(Color.GREEN);

        } else {
            mot_tv.setTextColor(Color.RED);
        }
    }


    public void useVehicleData(String vehMake, String vehModel, String vehYear, String vehFirstRegistered, String vehColour, String vehFuelType, String vehCylinderCapacity, String vehCo2Emissions, String vehMaxPower,
                               String vehAcceleration, String vehTopSpeed, String vehMaxTorque, String vehUrban, String vehExtraUrban, String vehCombined, String vehTransmission, String vehGears, String vehDriveTrain,
                               String vehLitres, String vehCylinders, String vehDoors, String vehBodyStyle, String vehSeats, String vehHeight, String vehWidth, String vehLength, String vehWheelBase) {

        make = vehMake;
        model = vehModel;
        year = vehYear;
        firstRegistered = vehFirstRegistered;
        colour = vehColour;
        fuelType = vehFuelType;
        cylinderCapacity = vehCylinderCapacity;
        co2Emissions = vehCo2Emissions;
        maxPower = vehMaxPower;
        notToMax = vehAcceleration;
        topSpeed = vehTopSpeed;
        maxTorque = vehMaxTorque;
        urban = vehUrban;
        extraUrban = vehExtraUrban;
        combined = vehCombined;
        transmission = vehTransmission;
        gears = vehGears;
        driveTrain = vehDriveTrain;
        noCylinders = vehCylinders;
        litres = vehLitres;
        doors = vehDoors;
        seats = vehSeats;
        bodyStyle = vehBodyStyle;
        height = vehHeight;
        width = vehWidth;
        length = vehLength;
        wheelbase = vehWheelBase;

        basicInfo.add("Make: " + make);
        basicInfo.add("Model: " + model);
        basicInfo.add("Year: " + year);
        basicInfo.add("First Registered: " + firstRegistered);
        basicInfo.add("Colour: " + colour);
        basicInfo.add("Fuel Type: " + fuelType);
        basicInfo.add("Cylinder Capacity: " + cylinderCapacity);
        basicInfo.add("CO2 Emissions: " + co2Emissions);
        performance.add("Max Power (BHP): " + maxPower);
        performance.add("Max Torque (Nm): " + maxTorque);
        performance.add("0-60 Mph: " + notToMax);
        performance.add("Top Speed (Mph): " + topSpeed);
        economy.add("Urban (Mpg): " + urban);
        economy.add("Extra-Urban (Mpg): " + extraUrban);
        economy.add("Combined (Mpg): " + combined);
        engineAndGearbox.add("Transmission: " + transmission);
        engineAndGearbox.add("Gears:" + gears);
        engineAndGearbox.add("Drive Train: " + driveTrain);
        engineAndGearbox.add("No of Cylinders: " + noCylinders);
        engineAndGearbox.add("Engine Capacity: " + litres);
        chassis.add("No of Doors: " + doors);
        chassis.add("No of Seats: " + seats);
        chassis.add("Body Style: " + bodyStyle);
        dimensions.add("Height: " + height);
        dimensions.add("Width: " + width);
        dimensions.add("Length: " + length);
        dimensions.add("Wheelbase: " + wheelbase);


        mAm_tv.setText(String.format("%s - %s", make, model));


    }

    private void useVehicleImage(String vehImg) {

        carImage = vehImg;

        Picasso.get().load(carImage).into(imageView);

        if (carImage == null){
            Toast.makeText(context, "No image found do the search again", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }


    }


    public void useDocId(String docId){
        documentId = docId;
        VehRepository vehicleRepository = new VehRepository((Application) getApplicationContext());
        DocId dId = new DocId(documentId);
        vehicleRepository.insertDocId(dId);


    }

    private void saveDataToDB() {

        //Save Data to Database
        VehicleTax vehicleTax = new VehicleTax(isTaxed, taxDue);
        VehicleMot vehicleMot = new VehicleMot(validMot, motDue);
        VehicleData vehicleData = new VehicleData(make, model, year, firstRegistered, colour, fuelType, cylinderCapacity,
                co2Emissions, maxPower, notToMax, topSpeed, maxTorque, urban, extraUrban, combined,
                transmission, gears, driveTrain, noCylinders, litres, doors, seats, bodyStyle, height, width, length, wheelbase);
        VehicleImage vehicleImage = new VehicleImage(carImage);



        VehRepository vehicleRepository = new VehRepository((Application) getApplicationContext());
        vehicleRepository.insertTax(vehicleTax);
        vehicleRepository.insertMot(vehicleMot);
        vehicleRepository.insert(vehicleData);
        vehicleRepository.insertImg(vehicleImage);


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user!= null){
            // Access a Cloud Firestore instance from your Activity
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("vehicle")
                    .add(vehicleData)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(CarDetailScreen.this, "Data saved successfully to Firebase", Toast.LENGTH_SHORT).show();
                            String docId =  documentReference.getId();


                            useDocId(docId);


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CarDetailScreen.this, "Data save failed!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }else {
            Toast.makeText(this, "Data  Not saved to Cloud", Toast.LENGTH_SHORT).show();
        }



    }

    public void ShowTaxPopup() {

        popup.setContentView(R.layout.pop_up_tax);
        close_popup_tax = popup.findViewById(R.id.close_popup_tax);
        popup_tax_tv = popup.findViewById(R.id.popup_tax_tv);
        popup_tax_tv.setText(taxDue);


        close_popup_tax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();
    }

    public void ShowMotPopup() {
        popup.setContentView(R.layout.pop_up_mot);
        close_popup_mot = popup.findViewById(R.id.close_popup_mot);
        popup_mot_tv = popup.findViewById(R.id.popup_mot_tv);
        popup_mot_tv.setText(motDue);

        close_popup_mot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();
    }





}
