package me.elmira.foursquareclient.venuedetails;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import java.util.List;

import me.elmira.foursquareclient.BaseViewModel;
import me.elmira.foursquareclient.data.VenuePhoto;
import me.elmira.foursquareclient.data.source.DataSource;

/**
 * Created by Elmira on 11/13/2017.
 */

public class VenueDetailsViewModel extends BaseViewModel {
    private static final int PHOTOS_LIMIT = 50;

    public final ObservableList<VenuePhoto> photos = new ObservableArrayList<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    public final ObservableBoolean emptyData = new ObservableBoolean(false);

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
}
