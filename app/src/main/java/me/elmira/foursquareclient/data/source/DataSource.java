package me.elmira.foursquareclient.data.source;

import android.content.Context;

import java.util.List;

import me.elmira.foursquareclient.data.City;
import me.elmira.foursquareclient.data.Venue;
import me.elmira.foursquareclient.data.VenuePhoto;

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

    interface LoadVenuePhotosCallback {
        void onVenuePhotosLoaded(List<VenuePhoto> venuePhotos);

        void onDataNotFound();
    }

    void loadCities(Context context, LoadCitiesCallback callback);

    void searchVenues(String latLng, int radius, int limit, SearchVenuesCallback callback);

    void loadVenuePhotos(String venueId, int limit, LoadVenuePhotosCallback callback);
}
