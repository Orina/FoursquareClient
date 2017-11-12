package me.elmira.foursquareclient.data.source.local;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.elmira.foursquareclient.data.City;
import me.elmira.foursquareclient.data.source.DataSource;
import me.elmira.foursquareclient.util.AppExecutors;

/**
 * Created by elmira on 11/9/17.
 */

@Singleton
public class LocalDataSource implements DataSource {

    private static final String LOG_TAG = "LocalDataSource";

    private AppExecutors mAppExecutors;

    @Inject
    public LocalDataSource(AppExecutors appExecutors) {
        this.mAppExecutors = appExecutors;
    }

    @Override
    public void loadCities(final Context context, final LoadCitiesCallback callback) {
        Log.d(LOG_TAG, "Loading cities...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    List<City> cities = LoadFileUtil.loadCities(context);

                    if (cities != null && cities.size() > 0) {
                        Log.d(LOG_TAG, "Cities were loaded, size: " + cities.size());
                        callback.onCitiesLoaded(cities);
                    }
                    else {
                        Log.d(LOG_TAG, "Cities were not found");
                        callback.onDataNotFound();
                    }
                } catch (Throwable ex) {
                    Log.e(LOG_TAG, "Exception during loading of cities: " + ex.getMessage(), ex);
                    callback.onDataNotFound();
                }
            }
        };
        mAppExecutors.fileIOThread().execute(runnable);
    }
}
