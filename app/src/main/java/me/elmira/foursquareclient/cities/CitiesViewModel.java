package me.elmira.foursquareclient.cities;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import java.lang.ref.WeakReference;
import java.util.List;

import me.elmira.foursquareclient.BaseViewModel;
import me.elmira.foursquareclient.model.City;
import me.elmira.foursquareclient.model.source.DataSource;

/**
 * Created by elmira on 11/9/17.
 */

public class CitiesViewModel extends BaseViewModel {

    public final ObservableList<City> data = new ObservableArrayList<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    public final ObservableBoolean emptyData = new ObservableBoolean(false);

    private WeakReference<Context> mContext;

    public CitiesViewModel(WeakReference<Context> mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onAttach() {
        loadCities();
    }

    @Override
    public void onDetach() {

    }

    private void loadCities() {
        Context context = mContext.get();
        if (context == null) return;

        dataLoading.set(true);

        mRepository.loadCities(context, new DataSource.LoadCitiesCallback() {
            @Override
            public void onCitiesLoaded(List<City> cities) {
                dataLoading.set(false);
                data.clear();
                data.addAll(cities);
            }

            @Override
            public void onDataNotFound() {
                dataLoading.set(false);
                emptyData.set(true);
            }
        });
    }
}
