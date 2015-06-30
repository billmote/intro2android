package com.androidfu.intro2android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObservationLocation {

    @Expose
    private String full;
    @Expose
    private String city;
    @Expose
    private String state;
    @Expose
    private String country;
    @SerializedName("country_iso3166")
    @Expose
    private String countryIso3166;
    @Expose
    private String latitude;
    @Expose
    private String longitude;
    @Expose
    private String elevation;

    /**
     * @return The full
     */
    public String getFull() {
        return full;
    }

    /**
     * @param full The full
     */
    public void setFull(String full) {
        this.full = full;
    }

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The countryIso3166
     */
    public String getCountryIso3166() {
        return countryIso3166;
    }

    /**
     * @param countryIso3166 The country_iso3166
     */
    public void setCountryIso3166(String countryIso3166) {
        this.countryIso3166 = countryIso3166;
    }

    /**
     * @return The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return The elevation
     */
    public String getElevation() {
        return elevation;
    }

    /**
     * @param elevation The elevation
     */
    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

}
