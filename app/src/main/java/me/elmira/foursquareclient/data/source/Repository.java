package me.elmira.foursquareclient.data.source;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.elmira.foursquareclient.data.City;
import me.elmira.foursquareclient.data.Venue;
import me.elmira.foursquareclient.data.VenuePhoto;

/**
 * Created by Elmira Andreeva on 11/8/17.
 */

@Singleton
public class Repository implements DataSource {
    private static final String LOG_TAG = "Repository";

    private final DataSource mRemoteDataSource;

    private final DataSource mLocalDataSource;

    private Map<Integer, City> mCachedCities = null;
    private Map<String, List<VenuePhoto>> mCachedVenuePhotos = null;

    private Map<String, List<String>> mCachedVenueMappings = null;
    private Map<String, Venue> mCachedVenues = null;

    @Inject
    public Repository(@Remote DataSource remoteDataSource, @Local DataSource localDataSource) {
        this.mRemoteDataSource = remoteDataSource;
        this.mLocalDataSource = localDataSource;

        mCachedCities = new LinkedHashMap<>();
        mCachedVenuePhotos = new HashMap<>();

        mCachedVenues = new LinkedHashMap<>();
        mCachedVenueMappings = new HashMap<>();
    }

    @Override
    public void loadCities(Context context, final LoadCitiesCallback callback) {
        Log.d(LOG_TAG, "Loading cities...");

        if (mCachedCities != null && mCachedCities.size() > 0) {
            Log.d(LOG_TAG, "Loading cities from memory cache");
            callback.onCitiesLoaded(new ArrayList<City>(mCachedCities.values()));
            return;
        }
        Log.d(LOG_TAG, "Loading cities from local json file");
        mLocalDataSource.loadCities(context, new LoadCitiesCallback() {
            @Override
            public void onCitiesLoaded(List<City> cities) {
                mCachedCities = new LinkedHashMap<>();
                for (City city : cities) {
                    mCachedCities.put(city.getId(), city);
                }
                callback.onCitiesLoaded(cities);
            }

            @Override
            public void onDataNotFound() {
                callback.onDataNotFound();
            }
        });
    }

    @Override
    public void searchVenues(final String latLng, int radius, int limit, final SearchVenuesCallback callback) {
        Log.d(LOG_TAG, "Start searching venues...[latLng: " + latLng + ", radius: " + radius + ", limit: " + limit);

        List<Venue> venues = new ArrayList<>();

        if (mCachedVenueMappings.containsKey(latLng)) {
            List<String> cachedVenueIds = mCachedVenueMappings.get(latLng);

            if (cachedVenueIds != null && cachedVenueIds.size() > 0) {
                for (String venueId : cachedVenueIds) {
                    venues.add(mCachedVenues.get(venueId));
                }
            }
        }
        if (venues.size() > 0) {
            Log.d(LOG_TAG, "Loaded venues from memory cache");
            callback.onVenuesFound(venues);
            return;
        }

        Log.d(LOG_TAG, "Loading venues from Web API");
        mRemoteDataSource.searchVenues(latLng, radius, limit, new SearchVenuesCallback() {
            @Override
            public void onVenuesFound(List<Venue> venues) {
                List<String> ids = new ArrayList<>();
                for (Venue venue : venues) {
                    mCachedVenues.put(venue.getId(), venue);
                    ids.add(venue.getId());
                }
                mCachedVenueMappings.put(latLng, ids);
                callback.onVenuesFound(venues);
            }

            @Override
            public void onDataNotFound() {
                callback.onDataNotFound();
            }
        });
    }

    @Override
    public void loadVenuePhotos(final String venueId, final int limit, final LoadVenuePhotosCallback callback) {
        if (mCachedVenuePhotos.containsKey(venueId)) {
            Log.d(LOG_TAG, "Loaded venue photos from memory cache");
            callback.onVenuePhotosLoaded(mCachedVenuePhotos.get(venueId));
            return;
        }
        mRemoteDataSource.loadVenuePhotos(venueId, limit, new LoadVenuePhotosCallback() {
            @Override
            public void onVenuePhotosLoaded(List<VenuePhoto> venuePhotos) {

                if (venuePhotos != null && venuePhotos.size() > 0) {
                    List<VenuePhoto> list = new ArrayList<>(venuePhotos);
                    mCachedVenuePhotos.put(venueId, list);
                    callback.onVenuePhotosLoaded(list);
                } else {
                    callback.onDataNotFound();
                }
            }

            @Override
            public void onDataNotFound() {
                callback.onDataNotFound();
            }
        });
    }
}
