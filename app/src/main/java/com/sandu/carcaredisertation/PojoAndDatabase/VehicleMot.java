package com.sandu.carcaredisertation.PojoAndDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vehicle_mot")
public class VehicleMot {

    @PrimaryKey(autoGenerate = true)
    int id;
    boolean vehHasMot;
    String vehNextMotDue;

    public VehicleMot(boolean vehHasMot, String vehNextMotDue) {
        this.vehHasMot = vehHasMot;
        this.vehNextMotDue = vehNextMotDue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getVehHasMot() {
        return vehHasMot;
    }

    public void setVehHasMot(boolean vehHasMot) {
        this.vehHasMot = vehHasMot;
    }

    public String getVehNextMotDue() {
        return vehNextMotDue;
    }

    public void setVehNextMotDue(String vehNextMotDue) {
        this.vehNextMotDue = vehNextMotDue;
    }
}
