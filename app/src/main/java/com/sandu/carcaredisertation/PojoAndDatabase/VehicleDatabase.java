package com.sandu.carcaredisertation.PojoAndDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {VehicleData.class, VehicleImage.class, VehicleTax.class, VehicleMot.class}, version = 5)
public abstract class VehicleDatabase extends RoomDatabase {

    private static VehicleDatabase instance;
    public abstract VehDao vehDao();
    public static  synchronized VehicleDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    VehicleDatabase.class, "vehicle_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
