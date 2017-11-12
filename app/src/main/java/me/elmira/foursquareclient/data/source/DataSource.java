package me.elmira.foursquareclient.data.source;

import android.content.Context;

import java.util.List;

import me.elmira.foursquareclient.data.City;
import me.elmira.foursquareclient.data.Venue;

/**
 * Created by elmira on 11/8/17.
 */


public interface DataSource {

    interface LoadCitiesCallback {

        void onCitiesLoaded(List<City> cities);

        void onDataNotFound();
    }

    interface SearchVenuesCallback {

        void onVenuesFound(List<Venue> venues);

        void onDataNotFound();
    }

    interface LoadVenueDetailsCallback {

        void onVenueLoaded(Venue venue);

        void onDataNotFound();
    }

    void loadCities(Context context, LoadCitiesCallback callback);

}
