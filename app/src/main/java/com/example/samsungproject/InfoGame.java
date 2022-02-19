package com.example.samsungproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoGame {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("haract")
    @Expose
    private String haract;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("cpu")
    @Expose
    private  String cpu;
    @SerializedName("motherboard")
    @Expose
    private String motherboard;
    @SerializedName("cooler")
    @Expose
    private String cooler;
    @SerializedName("ram")
    @Expose
    private String ram;
    @SerializedName("videoCard")
    @Expose
    private String videoCard;
    @SerializedName("HDD")
    @Expose
    private String HDD;
    @SerializedName("SSD")
    @Expose
    private String SSD;
    @SerializedName("opticalDrive")
    @Expose
    private String opticalDrive;
    @SerializedName("housing")
    @Expose
    private String housing;
    @SerializedName("powerSupply")
    @Expose
    private String powerSupply;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHaract() {
        return haract;
    }

    public void setHaract(String haract) {
        this.haract = haract;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getCooler() {
        return cooler;
    }

    public void setCooler(String cooler) {
        this.cooler = cooler;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(String videoCard) {
        this.videoCard = videoCard;
    }

    public String getHDD() {
        return HDD;
    }

    public void setHDD(String HDD) {
        this.HDD = HDD;
    }

    public String getSSD() {
        return SSD;
    }

    public void setSSD(String SSD) {
        this.SSD = SSD;
    }

    public String getOpticalDrive() {
        return opticalDrive;
    }

    public void setOpticalDrive(String opticalDrive) {
        this.opticalDrive = opticalDrive;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }
}
