package me.elmira.foursquareclient.data.source.remote;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.elmira.foursquareclient.data.VenuePhoto;
import me.elmira.foursquareclient.data.source.DataSource;
import me.elmira.foursquareclient.data.source.remote.data.ExploreResponse;
import me.elmira.foursquareclient.data.source.remote.data.Item;
import me.elmira.foursquareclient.data.source.remote.data.Photos;
import me.elmira.foursquareclient.data.source.remote.data.VenuePhotosResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by elmira on 11/9/17.
 */

@Singleton
public class RemoteDataSource implements DataSource {
    public static final String PHOTO_SIZE_100 = "100x100";
    public static final String PHOTO_SIZE_300 = "300x300";
    private static final String LOG_TAG = "RemoteDataSource";
    private static final String VERSION_DATE = "20171111";
    private final String clientId;
    private final String clientSecret;
    private FoursquareService mFoursquareService;

    @Inject
    public RemoteDataSource(FoursquareService foursquareService, String clientId, String clientSecret) {
        this.mFoursquareService = foursquareService;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public void loadCities(Context context, LoadCitiesCallback callback) {
        throw new UnsupportedOperationException("The operation is not supported on remote DS");
    }

    @Override
    public void searchVenues(String latLng, int radius, int limit, final SearchVenuesCallback callback) {
        Log.d(LOG_TAG, "Start searching venues...[latLng: " + latLng + ", radius: " + radius + ", limit: " + limit);

        Call<ExploreResponse> responseCall = mFoursquareService.exploreVenues(clientId, clientSecret, latLng, radius, limit, 1, VERSION_DATE);
        responseCall.enqueue(new Callback<ExploreResponse>() {
            @Override
            public void onResponse(Call<ExploreResponse> call, Response<ExploreResponse> response) {
                Log.d(LOG_TAG, "onResponse() isSuccessful: " + response.isSuccessful() +
                        ", error: " + response.errorBody());
                Log.d(LOG_TAG, "response body: " + response.toString());

                //this method is executed on the main thread
                ExploreResponse searchResponse = response.body();
                if (searchResponse == null || searchResponse.getResponse() == null
                        || searchResponse.getResponse().getGroups() == null) {
                    callback.onDataNotFound();
                    return;
                }

                List<me.elmira.foursquareclient.data.Venue> resultVenues = new ArrayList<>();
                for (Item item : searchResponse.getResponse().getGroups().get(0).getItems()) {
                    resultVenues.add(new me.elmira.foursquareclient.data.Venue(item.getVenue(), item.getTips()));
                }

                if (resultVenues.size() == 0) {
                    callback.onDataNotFound();
                } else {
                    callback.onVenuesFound(resultVenues);
                }
            }

            @Override
            public void onFailure(Call<ExploreResponse> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage(), t);
                callback.onDataNotFound();
            }
        });
    }

    @Override
    public void loadVenuePhotos(String venueId, int limit, final LoadVenuePhotosCallback callback) {
        Call<VenuePhotosResponse> venuePhotosCall = mFoursquareService.getVenuePhotos(venueId,
                clientId, clientSecret, limit, VERSION_DATE);
        venuePhotosCall.enqueue(new Callback<VenuePhotosResponse>() {
            @Override
            public void onResponse(Call<VenuePhotosResponse> call, Response<VenuePhotosResponse> response) {
                VenuePhotosResponse venuePhotosResponse = response.body();
                List<VenuePhoto> photos = new ArrayList<>();
                if (venuePhotosResponse != null) {
                    Photos photoList = venuePhotosResponse.getResponse().getPhotos();
                    if (photoList != null && photoList.getItems() != null) {
                        for (Item item : photoList.getItems()) {

                            photos.add(new VenuePhoto(
                                    item.getPrefix() + PHOTO_SIZE_300 + item.getSuffix(),
                                    (float) item.getWidth() / item.getHeight()));
                        }
                    }
                }
                if (photos.size() > 0) {
                    callback.onVenuePhotosLoaded(photos);
                } else {
                    callback.onDataNotFound();
                }
            }

            @Override
            public void onFailure(Call<VenuePhotosResponse> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage(), t);
                callback.onDataNotFound();
            }
        });
    }
}
