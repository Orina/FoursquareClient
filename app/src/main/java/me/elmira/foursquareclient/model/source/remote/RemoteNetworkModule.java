package me.elmira.foursquareclient.model.source.remote;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.elmira.foursquareclient.model.source.Remote;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by elmira on 11/10/17.
 */

@Module
public class RemoteNetworkModule {

    private String mBaseUrl;
    private String mClientId;
    private String mClientSecret;

    // Constructor needs one parameter to instantiate.
    public RemoteNetworkModule(String baseUrl, String clientId, String clientSecret) {
        this.mBaseUrl = baseUrl;
        this.mClientId = clientId;
        this.mClientSecret = clientSecret;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    FoursquareService provideFoursquareService(Retrofit retrofit) {
        return retrofit.create(FoursquareService.class);
    }

    @Singleton
    @Provides
    @Remote
    RemoteDataSource provideRemoteDataSource(FoursquareService foursquareService) {
        return new RemoteDataSource(foursquareService, mClientId, mClientSecret);
    }
}