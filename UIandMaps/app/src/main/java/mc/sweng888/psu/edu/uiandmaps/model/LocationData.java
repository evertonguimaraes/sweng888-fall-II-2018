package mc.sweng888.psu.edu.uiandmaps.model;

public class LocationData {

    private Double latitude;
    private Double longitude;
    private String location;

    public LocationData() {
    }

    public LocationData(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationData(Double latitude, Double longitude, String location) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getLocation() {
        return location;
    }
}
