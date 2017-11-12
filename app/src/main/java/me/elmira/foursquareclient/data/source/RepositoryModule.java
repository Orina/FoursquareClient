package me.elmira.foursquareclient.data.source;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.elmira.foursquareclient.data.source.local.LocalDataSource;
import me.elmira.foursquareclient.data.source.remote.FoursquareService;
import me.elmira.foursquareclient.data.source.remote.RemoteDataSource;
import me.elmira.foursquareclient.util.AppExecutors;

/**
 * Created by elmira on 11/10/17.
 */

@Module
public class RepositoryModule {

    private static final int THREAD_COUNT = 5;

    @Singleton
    @Provides
    static AppExecutors provideExecutors() {
        return new AppExecutors(Executors.newFixedThreadPool(THREAD_COUNT),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new AppExecutors.MainThreadExecutor());
    }

    @Singleton
    @Provides
    @Local
    LocalDataSource provideLocalDataSource(AppExecutors appExecutors){
        return new LocalDataSource(appExecutors);
    }

    @Singleton
    @Provides
    @Remote
    RemoteDataSource provideRemoteDataSource(FoursquareService foursquareService){
        return new RemoteDataSource(foursquareService);
    }

    @Singleton
    @Provides
    Repository provideRepository(@Remote RemoteDataSource remoteDataSource, @Local LocalDataSource localDataSource){
        return new Repository(remoteDataSource, localDataSource);
    }
}
