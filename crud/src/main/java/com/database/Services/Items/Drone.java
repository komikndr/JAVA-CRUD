package com.database.Services.Items;

public class Drone{
    private int DroneId;
    private String OwnerName;
    private String DroneClass;
    private double Speed;

    //Null
    public Drone(){

    }

    // Delete Drone
    public Drone(int DroneId) {
        this.DroneId = DroneId;
    }

    // Create Drone
    public Drone(String OwnerName, String DroneClass, double Speed){
        this.OwnerName = OwnerName;
        this.DroneClass = DroneClass;
        this.Speed = Speed;
    }
    
    // GetDrone
    public Drone(int DroneId, String OwnerName, String DroneClass, double Speed){
        this.DroneId = DroneId;
        this.OwnerName = OwnerName;
        this.DroneClass = DroneClass;
        this.Speed = Speed;
    }

    public int getDroneId() {
        return DroneId;
    }

    public void setDroneId(int DroneId) {
        this.DroneId = DroneId;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String OwnerName) {
        this.OwnerName = OwnerName;
    }

    public String getDroneClass() {
        return DroneClass;
    }

    public void setDroneClass(String DroneClass) {
        this.DroneClass = DroneClass;
    }

    public double getSpeed() {
        return Speed;
    }

    public void setSpeed(double Speed) {
        this.Speed = Speed;
    }

    @Override
    public String toString() {
        return "drone{" +
               "DroneId:" + DroneId +
                ", OwnerName:'" + OwnerName+ '\'' +
                ", DroneClass:" + DroneClass+
                ", Speed:" + Speed+
                '}';
    }
}

