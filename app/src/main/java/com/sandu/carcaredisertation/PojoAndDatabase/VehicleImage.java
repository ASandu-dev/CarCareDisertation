package com.sandu.carcaredisertation.PojoAndDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "veh_image")
public class VehicleImage {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String vehImg;

    public VehicleImage(String vehImg) {
        this.vehImg = vehImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehImg() {
        return vehImg;
    }

    public void setVehImg(String vehImg) {
        this.vehImg = vehImg;
    }
}
