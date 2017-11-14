package me.elmira.foursquareclient.venues;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import java.util.List;

import me.elmira.foursquareclient.BaseViewModel;
import me.elmira.foursquareclient.data.Venue;
import me.elmira.foursquareclient.data.source.DataSource;

/**
 * Created by elmira on 11/8/17.
 */

public class VenuesViewModel extends BaseViewModel {

    private static final int RADIUS = 1000;
    private static final int LIMIT = 25;

    public final ObservableList<Venue> items = new ObservableArrayList<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    public final ObservableBoolean emptyData = new ObservableBoolean(false);

    @Override
    public void onAttach() {

    }

    public void searchVenues(String latLng) {
        dataLoading.set(true);
        mRepository.searchVenues(latLng, RADIUS, LIMIT, new DataSource.SearchVenuesCallback() {
            @Override
            public void onVenuesFound(List<Venue> venues) {
                dataLoading.set(false);
                items.clear();
                items.addAll(venues);
            }

            @Override
            public void onDataNotFound() {
                dataLoading.set(false);
                emptyData.set(true);
            }
        });
    }

    @Override
    public void onDetach() {

    }
}
