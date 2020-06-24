package com.sandu.carcaredisertation.PojoAndDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class VehicleViewModel extends AndroidViewModel {

    private VehRepository repository;
    private LiveData<List<VehicleData>> allVehicles;

    public VehicleViewModel(@NonNull Application application) {
        super(application);

        repository = new VehRepository(application);
        allVehicles = repository.getAllVehicles();

    }

    LiveData<List<VehicleData>> getAllVehicles() {
        return allVehicles;
    }

    public void deleteCar(VehicleData vehicleData) {
        repository.deleteVehicle(vehicleData);
    }

    public void deleteImage(VehicleImage vehicleImage) {
        repository.deleteImage(vehicleImage);
    }
}
