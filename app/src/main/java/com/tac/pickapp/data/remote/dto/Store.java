package com.tac.pickapp.data.remote.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("seller_id")
    @Expose
    int sellerId;

    @SerializedName("dti")
    @Expose
    String dti;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("businessPermit")
    @Expose
    String businessPermit;

    @SerializedName("address")
    @Expose
    String address;

    @SerializedName("location")
    @Expose
    String location;

    @SerializedName("created_at")
    @Expose
    String createdAt;

    @SerializedName("updated_at")
    @Expose
    String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getDti() {
        return dti;
    }

    public void setDti(String dti) {
        this.dti = dti;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessPermit() {
        return businessPermit;
    }

    public void setBusinessPermit(String businessPermit) {
        this.businessPermit = businessPermit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
