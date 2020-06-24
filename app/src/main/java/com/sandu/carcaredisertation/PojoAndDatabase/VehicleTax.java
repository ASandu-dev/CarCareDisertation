package com.sandu.carcaredisertation.PojoAndDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vehicle_tax")
public class VehicleTax {
    @PrimaryKey(autoGenerate = true)
    int id;

    boolean vehTaxed;
    String vehExpiryDate;

    public VehicleTax(boolean vehTaxed, String vehExpiryDate) {
        this.vehTaxed = vehTaxed;
        this.vehExpiryDate = vehExpiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVehTaxed() {
        return vehTaxed;
    }

    public void setVehTaxed(boolean vehTaxed) {
        this.vehTaxed = vehTaxed;
    }

    public String getVehExpiryDate() {
        return vehExpiryDate;
    }

    public void setVehExpiryDate(String vehExpiryDate) {
        this.vehExpiryDate = vehExpiryDate;
    }
}
