package mc.sweng888.psu.edu.mapsandbroadcast.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class MapLocation implements Serializable {

    private String title;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
    private Double latitude;
    private Double longitude;

    public MapLocation() {
    }

    public MapLocation(String title, String description, Double latitude, Double longitude) {
        this.title = title;
        this.address = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MapLocation(String title, Double latitude, Double longitude){
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MapLocation(String title, String address, String city, String state, String country, String zip, Double latitude, Double longitude) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public LatLng getLatLng(){
        return new LatLng(latitude, longitude);
    }
    @Override
    public String toString() {
        return String.format("Place: %s, %s, %s, %s, %s. Coordinates - latitude (%.6f), longitude (%.6f)",
                address,
                city,
                state,
                country,
                zip,
                latitude,
                longitude);
    }
}
