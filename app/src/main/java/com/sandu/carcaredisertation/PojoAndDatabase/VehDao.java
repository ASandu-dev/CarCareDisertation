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

    @Insert
    void insertVehicle(VehicleData vehicleData);

    @Insert
    void insertImage(VehicleImage vehicleImage);

    @Update
    void updateVehicle(VehicleData vehicleData);

    @Update
    void updateImage(VehicleImage vehicleImage);

    @Delete
    void deleteVehicle(VehicleData vehicleData);

    @Delete
    void deleteImage(VehicleImage vehicleImage);

    @Query("DELETE FROM vehicle_database")
    void deleteAllVehicles();

    @Query("DELETE FROM veh_image")
    void deleteAllImages();

    @Query("SELECT * FROM vehicle_database")
    LiveData<List<VehicleData>> getVehicleData();

    @Query("SELECT * FROM veh_image")
    LiveData<List<VehicleImage>> getVehicleImage();

}
