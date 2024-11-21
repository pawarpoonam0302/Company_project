package com.demo.it;

public class Asset {

    private int assignedEmployeeId;
    private int assetId;
    private String assetName;

    public Asset(int assignedEmployeeId, int assetId, String assetName) {
        this.assignedEmployeeId = assignedEmployeeId;
        this.assetId = assetId;
        this.assetName = assetName;
    }



    public int getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(int assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assignedEmployeeId=" + assignedEmployeeId +
                ", assetId=" + assetId +
                ", assetName='" + assetName + '\'' +
                '}';
    }
}
