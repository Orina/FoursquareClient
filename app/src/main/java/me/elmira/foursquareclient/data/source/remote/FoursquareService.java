package me.elmira.foursquareclient.data.source.remote;

import java.util.List;

import me.elmira.foursquareclient.data.Venue;
import me.elmira.foursquareclient.data.VenueDetails;
import me.elmira.foursquareclient.data.VenuePhoto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by elmira on 11/10/17.
 */

public interface FoursquareService {

    String ENDPOINT = "https://api.foursquare.com/v2/";

    @GET("venues/search")
    Call<List<Venue>> searchVenue(@Query("client_id") String client_id,
                                  @Query("client_secret") String client_secret,
                                  @Query("ll") String latLng,
                                  @Query("radius") int radius,
                                  @Query("limit") int limit);

    @GET("venues/{VENUE_ID}")
    Call<VenueDetails> getVenueDetails(@Path("VENUE_ID") String venueId,
                                       @Query("client_id") String client_id,
                                       @Query("client_secret") String client_secret);

    @GET("venues/{VENUE_ID}/photos")
    Call<List<VenuePhoto>> getVenuePhotos(@Path("VENUE_ID") String venueId,
                                          @Query("client_id") String client_id,
                                          @Query("client_secret") String client_secret,
                                          @Query("limit") int limit);
}