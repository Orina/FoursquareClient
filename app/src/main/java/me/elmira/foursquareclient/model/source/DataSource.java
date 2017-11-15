package me.elmira.foursquareclient.model.source;

import android.content.Context;

import java.util.List;

import me.elmira.foursquareclient.model.City;
import me.elmira.foursquareclient.model.Venue;
import me.elmira.foursquareclient.model.VenuePhoto;

/**
 * Created by Elmira Andreeva on 11/8/17.
 */


public interface DataSource {

    void loadCities(Context context, LoadCitiesCallback callback);

    void searchVenues(String latLng, int radius, int limit, SearchVenuesCallback callback);

    void loadVenuePhotos(String venueId, int limit, LoadVenuePhotosCallback callback);

    void bookmarkVenue(String venueId, OnVenueBookmarkCallback callback);

    void loadVenueDetails(String venueId, LoadVenueDetailsCallback callback);

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

    interface OnVenueBookmarkCallback {
        void onSuccess(Venue newVenue);

        void onFailed();
    }
}
