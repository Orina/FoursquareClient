package me.elmira.foursquareclient.data.source.local;

import java.util.List;

import me.elmira.foursquareclient.data.City;

/**
 * Created by elmira on 11/9/17.
 */

public class CitiesJsonResponse {

    List<City> cities;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
