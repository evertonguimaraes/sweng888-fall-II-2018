package mc.sweng888.psu.edu.newmapsexample.model;

import java.io.Serializable;

public class MapLocation implements Serializable {

    private String title;
    private String description;
    private String latitude;
    private String longitude;

    public MapLocation() {
    }

    public MapLocation(String title, String description, String latitude, String longitude) {
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
