package me.elmira.foursquareclient.data.source;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.elmira.foursquareclient.data.City;

/**
 * Created by elmira on 11/8/17.
 */

@Singleton
public class Repository implements DataSource {

    private final DataSource mRemoteDataSource;

    private final DataSource mLocalDataSource;

    private Map<Integer, City> mCachedCities = null;

    @Inject
    public Repository(@Remote DataSource remoteDataSource, @Local DataSource localDataSource) {
        this.mRemoteDataSource = remoteDataSource;
        this.mLocalDataSource = localDataSource;
    }

    @Override
    public void loadCities(Context context, final LoadCitiesCallback callback) {
        if (mCachedCities != null) {
            callback.onCitiesLoaded(new ArrayList<City>(mCachedCities.values()));
            return;
        }

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
}
