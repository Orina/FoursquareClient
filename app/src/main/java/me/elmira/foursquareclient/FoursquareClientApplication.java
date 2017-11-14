package me.elmira.foursquareclient;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import me.elmira.foursquareclient.data.source.RepositoryModule;
import me.elmira.foursquareclient.data.source.remote.FoursquareService;
import me.elmira.foursquareclient.data.source.remote.RemoteNetworkModule;
import me.elmira.foursquareclient.di.AppModule;
import me.elmira.foursquareclient.di.DaggerDataComponent;
import me.elmira.foursquareclient.di.DataComponent;

/**
 * Created by elmira on 11/9/17.
 */

public class FoursquareClientApplication extends Application {

    private static FoursquareClientApplication app;

    private DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        app = this;

        dataComponent = DaggerDataComponent.builder()
                .remoteNetworkModule(new RemoteNetworkModule(FoursquareService.ENDPOINT,
                        getString(R.string.foursquare_client_id),
                        getString(R.string.foursquare_client_secret)))
                .repositoryModule(new RepositoryModule())
                .appModule(new AppModule(this))
                .build();

        dataComponent.inject(this);
    }

    public static FoursquareClientApplication getApp() {
        return app;
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }
}