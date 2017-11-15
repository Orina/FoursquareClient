package me.elmira.foursquareclient.venuedetails;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.databinding.ObservableParcelable;
import android.util.Log;

import java.util.List;

import me.elmira.foursquareclient.BR;
import me.elmira.foursquareclient.BaseViewModel;
import me.elmira.foursquareclient.model.Venue;
import me.elmira.foursquareclient.model.VenuePhoto;
import me.elmira.foursquareclient.model.source.DataSource;

/**
 * Created by Elmira on 11/13/2017.
 */

public class VenueDetailsViewModel extends BaseViewModel {
    private static final int PHOTOS_LIMIT = 50;
    private static final String LOG_TAG = "VenueDetailsVM";

    public final ObservableList<VenuePhoto> photos = new ObservableArrayList<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    public final ObservableBoolean emptyData = new ObservableBoolean(false);

    public final ObservableParcelable<Venue> venue = new ObservableParcelable<Venue>();

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

    public void loadVenuePhotos(String venueId) {
        dataLoading.set(true);
        mRepository.loadVenuePhotos(venueId, PHOTOS_LIMIT, new DataSource.LoadVenuePhotosCallback() {
            @Override
            public void onVenuePhotosLoaded(List<VenuePhoto> venuePhotos) {
                dataLoading.set(false);
                photos.clear();
                photos.addAll(venuePhotos);
            }

            @Override
            public void onDataNotFound() {
                dataLoading.set(false);
                emptyData.set(true);
            }
        });
    }

    public void loadVenue(String venueId) {
        dataLoading.set(true);
        mRepository.loadVenueDetails(venueId, new DataSource.LoadVenueDetailsCallback() {
            @Override
            public void onVenueLoaded(Venue newVenue) {
                dataLoading.set(false);
                venue.set(newVenue);
            }

            @Override
            public void onDataNotFound() {
                dataLoading.set(false);
                emptyData.set(true);
            }
        });
    }

    public void changeVenueBookmark(String venueId) {
        mRepository.bookmarkVenue(venueId, new DataSource.OnVenueBookmarkCallback() {
            @Override
            public void onSuccess(Venue newVenue) {
                Log.d(LOG_TAG, "on bookmark venue: "+ newVenue.isBookmarked());
                venue.set(newVenue);
                venue.notifyChange();
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
