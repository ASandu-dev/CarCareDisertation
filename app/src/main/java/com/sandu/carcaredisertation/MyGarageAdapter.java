package com.sandu.carcaredisertation;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandu.carcaredisertation.PojoAndDatabase.VehRepository;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleData;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleDatabase;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleImage;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleMot;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleTax;
import com.sandu.carcaredisertation.PojoAndDatabase.VehicleViewModel;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyGarageAdapter extends RecyclerView.Adapter<MyGarageAdapter.MyGarageHolder> {
    Context context;
    Application application;
    private VehicleData vehicleData;
    private VehicleDatabase vehicleDatabase;
    private VehRepository vehRepository;
    private List<VehicleTax> tax = new ArrayList<>();
    private List<VehicleMot> mot = new ArrayList<>();
    private List<VehicleData> cars = new ArrayList<>();
    private List<VehicleImage> image = new ArrayList<>();
    private VehicleViewModel vehicleViewModel;
    private String vehicle;
    private OnCarClickListener onCarClickListener;

    public MyGarageAdapter(List<VehicleData> cars, List<VehicleImage> image, OnCarClickListener onCarClickListener) {
        this.cars = cars;
        this.image = image;
        this.onCarClickListener = onCarClickListener;
    }

    @NonNull
    @Override
    public MyGarageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.garage_item, parent, false);
        return new MyGarageHolder(itemView, onCarClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyGarageHolder holder, int position) {

        if (cars != null) {
            VehicleData vehicleData = cars.get(position);
            holder.setData(vehicleData.getVehMake(), position);

            VehicleImage vehicleImage = image.get(position);
            holder.setCarImg(vehicleImage.getVehImg(), position);
        }


    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public void setCars(List<VehicleData> vehicleData) {
        cars = vehicleData;
        notifyDataSetChanged();
    }

    public void setImages(List<VehicleImage> vehicleImages) {
        image = vehicleImages;
        notifyDataSetChanged();
    }

    public void setTax(List<VehicleTax> vehicleTaxes){
        tax = vehicleTaxes;
        notifyDataSetChanged();
    }

    public void setMot(List<VehicleMot> vehicleMots){
        mot = vehicleMots;
        notifyDataSetChanged();
    }

    public VehicleData getVehicleAtPosition(int position) {
        return cars.get(position);
    }

    public VehicleImage getImageAtPosition(int position) {
        return image.get(position);
    }
    public VehicleTax getTaxAtPosition(int position){
        return tax.get(position);
    }
    public VehicleMot getMotAtPosition(int position){
        return mot.get(position);
    }


    class MyGarageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView carImg;
        private TextView carName;
        private int carPosition;
        private int imagePosition;
        private int taxPosition;
        private int motPosition;
        private int position;
        OnCarClickListener onCarClickListener;

        public MyGarageHolder (View itemView, OnCarClickListener onCarClickListener) {
            super(itemView);
            carImg = itemView.findViewById(R.id.rv_car_img);
            carName = itemView.findViewById(R.id.rv_car_name);
            this.onCarClickListener = onCarClickListener;
            itemView.setOnClickListener(this);

        }


        public void setData(String car, int position) {
            this.carName.setText(car);
            carPosition = position;

        }

        public void setCarImg(String img, int position) {

            Picasso.get().setLoggingEnabled(true);
            Picasso.get().load(img).noFade().memoryPolicy(MemoryPolicy.NO_CACHE).into(this.carImg);
            imagePosition = position;

        }

        @Override
        public void onClick(View v) {

            onCarClickListener.onCarClick(getAdapterPosition());
        }
    }
    public interface OnCarClickListener{
        void onCarClick(int position);
    }


}



