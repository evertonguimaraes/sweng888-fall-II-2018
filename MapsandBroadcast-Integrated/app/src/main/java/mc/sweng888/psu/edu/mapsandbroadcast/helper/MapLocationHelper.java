package mc.sweng888.psu.edu.mapsandbroadcast.helper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import mc.sweng888.psu.edu.mapsandbroadcast.model.MapLocation;

public class MapLocationHelper {

    // This method instantiate a MapLocation object based on the LatLng provided as input.
    // The Google API provides all location details based on the coordinates.
    public static MapLocation getAddressFromLatLgn(Context context, LatLng latLng){

        MapLocation mapLocation = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> locations = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);

            mapLocation = new MapLocation();
            mapLocation.setLatitude(latLng.latitude);
            mapLocation.setLongitude(latLng.longitude);
            mapLocation.setTitle(locations.get(0).getFeatureName());
            mapLocation.setAddress(locations.get(0).getAddressLine(0));
            mapLocation.setCity(locations.get(0).getLocality());
            mapLocation.setState(locations.get(0).getAdminArea());
            mapLocation.setCountry(locations.get(0).getCountryName());
            mapLocation.setZip(locations.get(0).getPostalCode());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapLocation;
    }

}
