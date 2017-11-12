package me.elmira.foursquareclient;

import android.databinding.BaseObservable;

import javax.inject.Inject;

import me.elmira.foursquareclient.data.source.Repository;

/**
 * Created by elmira on 11/9/17.
 */

public abstract class BaseViewModel extends BaseObservable {

    @Inject
    protected Repository mRepository;

    public BaseViewModel() {

        FoursquareClientApplication.getApp().getDataComponent().inject(this);
    }

    public abstract void onAttach();
    public abstract void onDetach();
}
