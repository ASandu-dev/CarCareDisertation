package com.sandu.carcaredisertation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sandu.carcaredisertation.Pojos_Databases.VehicleData;
import com.sandu.carcaredisertation.Pojos_Databases.VehicleDatabase;
import com.sandu.carcaredisertation.Pojos_Databases.VehicleRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;


public class CarDetailScreen extends AppCompatActivity {



    TextView mpg_tv, speed_tv, bhp_tv;
    private VehicleDatabase vehicleDatabase;
    private VehicleData vehicleData;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardetail_activity);

        vehicleDatabase = VehicleDatabase.getInstance(CarDetailScreen.this);
        context = this.getApplicationContext();

        mpg_tv = findViewById(R.id.mpg_tv);
        speed_tv = findViewById(R.id.speed_tv);
        bhp_tv = findViewById(R.id.bhp_tv);

//         Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //.....//

        jsonParse();
        getDataUI();



    }

    private void jsonParse(){

        final VehicleRepository repository;
        Intent intent = getIntent();
        String plate = intent.getStringExtra("plate");
        Log.e("test", String.valueOf(plate));
        String url = "https://uk1.ukvehicledata.co.uk/api/datapackage/VehicleData?v=2&api_nullitems=1&auth_apikey=b5d7757f-da24-47e5-8cec-03caa133cf6c&user_tag=&key_VRM="+plate;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait, while we load the data");
        progressDialog.show();

        //*Could not understand how to parse FARE objects*
        StringRequest request = new StringRequest(url, new com.android.volley.Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject responses = jsonObject.getJSONObject("Response");

                    JSONObject dataItems = responses.getJSONObject("DataItems");

                        JSONObject techDetails = dataItems.getJSONObject("TechnicalDetails");
                        JSONObject regDetails = dataItems.getJSONObject("VehicleRegistration");

                            JSONObject vehDimensions = techDetails.getJSONObject("Dimensions");
                            JSONObject vehPerformance = techDetails.getJSONObject("Performance");
                            JSONObject vehConsumption = techDetails.getJSONObject("Consumption");


                                JSONObject vehGeneral = techDetails.getJSONObject("General");
                                JSONObject vehPower = vehPerformance.getJSONObject("Power");
                                JSONObject vehMaxSpeed = vehPerformance.getJSONObject("MaxSpeed");

                                JSONObject vehConsumptionCombined = vehConsumption.getJSONObject("Combined");

                                    JSONObject vehEngine = vehGeneral.getJSONObject("Engine");



                    String mpg = vehConsumptionCombined.getString("Mpg");
                    String maxSpeed = vehMaxSpeed.getString("Mph");
                    String bhp = vehPower.getString("Bhp");
                    String vehLength = vehDimensions.getString("CarLength");
                    String vehWidth = vehDimensions.getString("Width");
                    String vehHeight = vehDimensions.getString("Height");
                    String vehMake = regDetails.getString("Make");
                    String vehModel = regDetails.getString("MakeModel");
                    String vehCylinders = vehEngine.getString("NumberOfCylinders");



//                    Log.e("test", mpg);
//                    Log.e("test", maxSpeed);
//                    Log.e("test", bhp);
//                    Log.e("test", vehLength);
//                    Log.e("test", vehWidth);
//                    Log.e("test", vehHeight);
//                    Log.e("test", vehMake);
//                    Log.e("test", vehModel);
//                    Log.e("test", vehCylinders);


                    VehicleRepository vehicleRepository = new VehicleRepository((Application) getApplicationContext());

                    vehicleRepository.insert(mpg, maxSpeed, bhp, vehLength, vehWidth, vehHeight, vehMake, vehModel, vehCylinders);


                }

                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    private void getDataUI(){


        VehicleRepository vehicleRepository = new VehicleRepository(getApplicationContext());


        vehicleRepository.getAllNotes().observe(CarDetailScreen.this, new Observer<List<VehicleData>>() {
            @Override
            public void onChanged(@Nullable List<VehicleData> vehicleData) {
                for(VehicleData v : vehicleData) {

                    mpg_tv.setText(String.valueOf(v.getMpg()));
                    speed_tv.setText(String.valueOf(v.getMaxSpeed())+ "/MPH");
                    bhp_tv.setText(String.valueOf(v.getBhp()));

                    Log.e("mpg", v.getMpg());
                    Log.e("maxSpeed", v.getMaxSpeed());
                    Log.e("bhp", v.getBhp());
                    Log.e("vehLength", v.getVehLength());
                    Log.e("vehWidth", v.getVehWidth());
                    Log.e("vehHeight", v.getVehHeight());
                    Log.e("vehMake", v.getVehMake());
                    Log.e("vehModel", v.getVehModel());
                    Log.e("vehCylinders", v.getVehCylinders());
                }
            }
        });
    }



}
