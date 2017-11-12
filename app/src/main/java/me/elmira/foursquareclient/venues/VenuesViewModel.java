package me.elmira.foursquareclient.venues;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import me.elmira.foursquareclient.data.Venue;

/**
 * Created by elmira on 11/8/17.
 */

public class VenuesViewModel extends BaseObservable {

    public final ObservableList<Venue> items = new ObservableArrayList<>();

    public void start(){

    }

    private void loadVenues(){

    }
}
