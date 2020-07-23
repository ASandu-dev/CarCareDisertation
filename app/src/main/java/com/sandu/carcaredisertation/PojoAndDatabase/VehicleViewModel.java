package com.sandu.carcaredisertation.PojoAndDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class VehicleViewModel extends AndroidViewModel {

    private VehRepository repository;
    private LiveData<List<VehicleData>> allVehicles;
    private LiveData<List<DocId>> allDocIds;

    public VehicleViewModel(@NonNull Application application) {
        super(application);

        repository = new VehRepository(application);
        allVehicles = repository.getAllVehicles();
        allDocIds = repository.getAllDocIds();

    }

    LiveData<List<VehicleData>> getAllVehicles() {
        return allVehicles;
    }

    LiveData<List<DocId>> getAllDocIds() {
        return allDocIds;
    }

    public void insertDocId(DocId docId){
        repository.insertDocId(docId);
    }



    public void deleteCar(VehicleData vehicleData) {
        repository.deleteVehicle(vehicleData);
    }

    public void deleteImage(VehicleImage vehicleImage) {
        repository.deleteImage(vehicleImage);
    }

    public void deleteDocId(DocId docId) {
        repository.deleteDocId(docId);
    }

}
