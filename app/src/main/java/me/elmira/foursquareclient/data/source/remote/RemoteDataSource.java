package me.elmira.foursquareclient.data.source.remote;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.elmira.foursquareclient.data.source.DataSource;

/**
 * Created by elmira on 11/9/17.
 */

@Singleton
public class RemoteDataSource implements DataSource {

    private FoursquareService mFoursquareService;

    @Inject
    public RemoteDataSource(FoursquareService foursquareService) {
        this.mFoursquareService = foursquareService;
    }

    @Override
    public void loadCities(Context context, LoadCitiesCallback callback) {
        throw new UnsupportedOperationException("The operation is not supported on remote DS");
    }
}
