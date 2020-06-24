package com.sandu.carcaredisertation.PojoAndDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "vehicle_database")
public class VehicleData {
    @PrimaryKey (autoGenerate = true)
    private int id;


    private String vehMake, vehModel, vehYear, vehFirstRegistered,
            vehColour, vehFuelType, vehCylinderCapacity, vehCo2Emissions,
            vehMaxPower, vehAcceleration, vehTopSpeed, vehMaxTorque, vehUrban,
            vehExtraUrban, vehCombined, vehTransmission, vehGears, vehDriveTrain,
            vehLitres, vehCylinders, vehDoors, vehBodyStyle, vehSeats, vehHeight,
            vehWidth, vehLength, vehWheelBase;

    public VehicleData(String vehMake, String vehModel, String vehYear, String vehFirstRegistered, String vehColour, String vehFuelType,
                       String vehCylinderCapacity, String vehCo2Emissions, String vehMaxPower, String vehAcceleration, String vehTopSpeed,
                       String vehMaxTorque, String vehUrban, String vehExtraUrban, String vehCombined, String vehTransmission, String vehGears,
                       String vehDriveTrain, String vehLitres, String vehCylinders, String vehDoors, String vehBodyStyle, String vehSeats,
                       String vehHeight, String vehWidth, String vehLength, String vehWheelBase) {
        this.vehMake = vehMake;
        this.vehModel = vehModel;
        this.vehYear = vehYear;
        this.vehFirstRegistered = vehFirstRegistered;
        this.vehColour = vehColour;
        this.vehFuelType = vehFuelType;
        this.vehCylinderCapacity = vehCylinderCapacity;
        this.vehCo2Emissions = vehCo2Emissions;
        this.vehMaxPower = vehMaxPower;
        this.vehAcceleration = vehAcceleration;
        this.vehTopSpeed = vehTopSpeed;
        this.vehMaxTorque = vehMaxTorque;
        this.vehUrban = vehUrban;
        this.vehExtraUrban = vehExtraUrban;
        this.vehCombined = vehCombined;
        this.vehTransmission = vehTransmission;
        this.vehGears = vehGears;
        this.vehDriveTrain = vehDriveTrain;
        this.vehLitres = vehLitres;
        this.vehCylinders = vehCylinders;
        this.vehDoors = vehDoors;
        this.vehBodyStyle = vehBodyStyle;
        this.vehSeats = vehSeats;
        this.vehHeight = vehHeight;
        this.vehWidth = vehWidth;
        this.vehLength = vehLength;
        this.vehWheelBase = vehWheelBase;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getVehYear() {
        return vehYear;
    }

    public void setVehYear(String vehYear) {
        this.vehYear = vehYear;
    }

    public String getVehFirstRegistered() {
        return vehFirstRegistered;
    }

    public void setVehFirstRegistered(String vehFirstRegistered) {
        this.vehFirstRegistered = vehFirstRegistered;
    }

    public String getVehColour() {
        return vehColour;
    }

    public void setVehColour(String vehColour) {
        this.vehColour = vehColour;
    }

    public String getVehFuelType() {
        return vehFuelType;
    }

    public void setVehFuelType(String vehFuelType) {
        this.vehFuelType = vehFuelType;
    }

    public String getVehCylinderCapacity() {
        return vehCylinderCapacity;
    }

    public void setVehCylinderCapacity(String vehCylinderCapacity) {
        this.vehCylinderCapacity = vehCylinderCapacity;
    }

    public String getVehCo2Emissions() {
        return vehCo2Emissions;
    }

    public void setVehCo2Emissions(String vehCo2Emissions) {
        this.vehCo2Emissions = vehCo2Emissions;
    }

    public String getVehMaxPower() {
        return vehMaxPower;
    }

    public void setVehMaxPower(String vehMaxPower) {
        this.vehMaxPower = vehMaxPower;
    }

    public String getVehAcceleration() {
        return vehAcceleration;
    }

    public void setVehAcceleration(String vehAcceleration) {
        this.vehAcceleration = vehAcceleration;
    }

    public String getVehTopSpeed() {
        return vehTopSpeed;
    }

    public void setVehTopSpeed(String vehTopSpeed) {
        this.vehTopSpeed = vehTopSpeed;
    }

    public String getVehMaxTorque() {
        return vehMaxTorque;
    }

    public void setVehMaxTorque(String vehMaxTorque) {
        this.vehMaxTorque = vehMaxTorque;
    }

    public String getVehUrban() {
        return vehUrban;
    }

    public void setVehUrban(String vehUrban) {
        this.vehUrban = vehUrban;
    }

    public String getVehExtraUrban() {
        return vehExtraUrban;
    }

    public void setVehExtraUrban(String vehExtraUrban) {
        this.vehExtraUrban = vehExtraUrban;
    }

    public String getVehCombined() {
        return vehCombined;
    }

    public void setVehCombined(String vehCombined) {
        this.vehCombined = vehCombined;
    }

    public String getVehTransmission() {
        return vehTransmission;
    }

    public void setVehTransmission(String vehTransmission) {
        this.vehTransmission = vehTransmission;
    }

    public String getVehGears() {
        return vehGears;
    }

    public void setVehGears(String vehGears) {
        this.vehGears = vehGears;
    }

    public String getVehDriveTrain() {
        return vehDriveTrain;
    }

    public void setVehDriveTrain(String vehDriveTrain) {
        this.vehDriveTrain = vehDriveTrain;
    }

    public String getVehLitres() {
        return vehLitres;
    }

    public void setVehLitres(String vehLitres) {
        this.vehLitres = vehLitres;
    }

    public String getVehCylinders() {
        return vehCylinders;
    }

    public void setVehCylinders(String vehCylinders) {
        this.vehCylinders = vehCylinders;
    }

    public String getVehDoors() {
        return vehDoors;
    }

    public void setVehDoors(String vehDoors) {
        this.vehDoors = vehDoors;
    }

    public String getVehBodyStyle() {
        return vehBodyStyle;
    }

    public void setVehBodyStyle(String vehBodyStyle) {
        this.vehBodyStyle = vehBodyStyle;
    }

    public String getVehSeats() {
        return vehSeats;
    }

    public void setVehSeats(String vehSeats) {
        this.vehSeats = vehSeats;
    }

    public String getVehHeight() {
        return vehHeight;
    }

    public void setVehHeight(String vehHeight) {
        this.vehHeight = vehHeight;
    }

    public String getVehWidth() {
        return vehWidth;
    }

    public void setVehWidth(String vehWidth) {
        this.vehWidth = vehWidth;
    }

    public String getVehLength() {
        return vehLength;
    }

    public void setVehLength(String vehLength) {
        this.vehLength = vehLength;
    }

    public String getVehWheelBase() {
        return vehWheelBase;
    }

    public void setVehWheelBase(String vehWheelBase) {
        this.vehWheelBase = vehWheelBase;
    }


}

