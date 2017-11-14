package me.elmira.foursquareclient.model.source.remote;

import me.elmira.foursquareclient.model.VenueDetails;
import me.elmira.foursquareclient.model.source.remote.data.ExploreResponse;
import me.elmira.foursquareclient.model.source.remote.data.VenuePhotosResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by elmira on 11/10/17.
 */

public interface FoursquareService {

    String ENDPOINT = "https://api.foursquare.com/v2/";

    @GET("venues/explore")
    Call<ExploreResponse> exploreVenues(@Query("client_id") String client_id,
                                        @Query("client_secret") String client_secret,
                                        @Query("ll") String latLng,
                                        @Query("radius") int radius,
                                        @Query("limit") int limit,
                                        @Query("venuePhotos") int venuePhotos,
                                        @Query("v") String versionDate);

    @GET("venues/{VENUE_ID}")
    Call<VenueDetails> getVenueDetails(@Path("VENUE_ID") String venueId,
                                       @Query("client_id") String client_id,
                                       @Query("client_secret") String client_secret,
                                       @Query("v") String versionDate);

    @GET("venues/{VENUE_ID}/photos")
    Call<VenuePhotosResponse> getVenuePhotos(@Path("VENUE_ID") String venueId,
                                             @Query("client_id") String client_id,
                                             @Query("client_secret") String client_secret,
                                             @Query("limit") int limit,
                                             @Query("v") String versionDate);
}