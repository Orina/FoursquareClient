package me.elmira.foursquareclient.data;

/**
 * Created by elmira on 11/8/17.
 */

public class City {

    private int id;
    private String name;
    private double lat;
    private double lng;

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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String generateLatLng() {
        return lat + "," + lng;
    }
}