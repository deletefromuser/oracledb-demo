package com.example.oracledbdemo.dao.model;

public class Countries {
    private String countryId;

    private String countryName;

    private Short regionId;

    public Countries(String countryId, String countryName, Short regionId) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.regionId = regionId;
    }

    public Countries() {
        super();
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId == null ? null : countryId.trim();
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    public Short getRegionId() {
        return regionId;
    }

    public void setRegionId(Short regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", countryId=").append(countryId);
        sb.append(", countryName=").append(countryName);
        sb.append(", regionId=").append(regionId);
        sb.append("]");
        return sb.toString();
    }
}