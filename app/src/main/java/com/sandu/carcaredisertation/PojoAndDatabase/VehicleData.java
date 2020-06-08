package com.sandu.carcaredisertation.PojoAndDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "vehicle_database")
public class VehicleData {
    @PrimaryKey (autoGenerate = true)
    private int id;


    private String mpg;
    private String maxSpeed;
    private String bhp;
    private String vehLength;
    private String vehWidth;
    private String vehHeight;
    private String vehMake;
    private String vehModel;
    private String vehCylinders;


    public VehicleData(String mpg, String maxSpeed, String bhp, String vehLength, String vehWidth, String vehHeight, String vehMake, String vehModel, String vehCylinders) {
        this.mpg = mpg;
        this.maxSpeed = maxSpeed;
        this.bhp = bhp;
        this.vehLength = vehLength;
        this.vehWidth = vehWidth;
        this.vehHeight = vehHeight;
        this.vehMake = vehMake;
        this.vehModel = vehModel;
        this.vehCylinders = vehCylinders;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMpg() {
        return mpg;
    }

    public void setMpg(String mpg) {
        this.mpg = mpg;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getBhp() {
        return bhp;
    }

    public void setBhp(String bhp) {
        this.bhp = bhp;
    }

    public String getVehLength() {
        return vehLength;
    }

    public void setVehLength(String vehLength) {
        this.vehLength = vehLength;
    }

    public String getVehWidth() {
        return vehWidth;
    }

    public void setVehWidth(String vehWidth) {
        this.vehWidth = vehWidth;
    }

    public String getVehHeight() {
        return vehHeight;
    }

    public void setVehHeight(String vehHeight) {
        this.vehHeight = vehHeight;
    }

    public String getVehMake() {
        return vehMake;
    }

    public void setVehMake(String vehMake) {
        this.vehMake = vehMake;
    }

    public String getVehModel() {
        return vehModel;
    }

    public void setVehModel(String vehModel) {
        this.vehModel = vehModel;
    }

    public String getVehCylinders() {
        return vehCylinders;
    }

    public void setVehCylinders(String vehCylinders) {
        this.vehCylinders = vehCylinders;
    }
}

