package me.elmira.foursquareclient.di;

import javax.inject.Singleton;

import dagger.Component;
import me.elmira.foursquareclient.BaseViewModel;
import me.elmira.foursquareclient.FoursquareClientApplication;
import me.elmira.foursquareclient.model.source.RepositoryModule;
import me.elmira.foursquareclient.model.source.remote.RemoteNetworkModule;

/**
 * Created by elmira on 11/10/17.
 */

@Singleton
@Component(modules = {AppModule.class, RemoteNetworkModule.class, RepositoryModule.class})

public interface DataComponent {

    void inject(BaseViewModel viewModel);

    void inject(FoursquareClientApplication application);

}