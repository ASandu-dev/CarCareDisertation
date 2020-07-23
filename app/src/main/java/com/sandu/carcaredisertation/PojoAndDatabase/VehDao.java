package com.sandu.carcaredisertation.PojoAndDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface VehDao {

    //Insert
    @Insert
    void insertVehicle(VehicleData vehicleData);

    @Insert
    void insertImage(VehicleImage vehicleImage);

    @Insert
    void insertTax(VehicleTax vehicleTax);

    @Insert
    void insertMot(VehicleMot vehicleMot);

    @Insert
    void insertDocId(DocId docId);

    //Update

    @Update
    void updateVehicle(VehicleData vehicleData);

    @Update
    void updateImage(VehicleImage vehicleImage);

    @Update
    void updateDocId(DocId docId);

    //Delete

    @Delete
    void deleteVehicle(VehicleData vehicleData);

    @Delete
    void deleteImage(VehicleImage vehicleImage);

    @Delete
    void deleteTax(VehicleTax vehicleTax);

    @Delete
    void deleteMot(VehicleMot vehicleMot);

    @Delete
    void deleteDocId(DocId docId);

    //Query

    @Query("DELETE FROM vehicle_database")
    void deleteAllVehicles();

    @Query("DELETE FROM veh_image")
    void deleteAllImages();

    @Query("DELETE FROM vehicle_tax")
    void deleteAllTaxes();

    @Query("DELETE FROM vehicle_mot")
    void deleteAllMots();

    @Query("DELETE FROM doc_id")
    void deleteAllDocIds();

    //Query Live data

    @Query("SELECT * FROM vehicle_database")
    LiveData<List<VehicleData>> getVehicleData();

    @Query("SELECT * FROM veh_image")
    LiveData<List<VehicleImage>> getVehicleImage();

    @Query("SELECT * FROM vehicle_tax")
    LiveData<List<VehicleTax>> getVehicleTax();

    @Query("SELECT * FROM vehicle_mot")
    LiveData<List<VehicleMot>> getVehicleMot();

    @Query("SELECT * FROM doc_id")
    LiveData<List<DocId>> getDocId();

}
