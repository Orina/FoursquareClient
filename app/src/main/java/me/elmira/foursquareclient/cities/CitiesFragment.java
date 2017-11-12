package me.elmira.foursquareclient.cities;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import me.elmira.foursquareclient.R;
import me.elmira.foursquareclient.data.City;
import me.elmira.foursquareclient.databinding.FragmentCityListBinding;

/**
 * Created by elmira on 11/9/17.
 */

public class CitiesFragment extends Fragment {

    private CitiesViewModel mViewModel;

    private FragmentCityListBinding mBinding;
    private CitiesAdapter mAdapter;

    private CitiesListChangeCallback mCitiesListChangeCallback;

    public CitiesFragment() {
    }

    public static CitiesFragment newInstance() {
        return new CitiesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_list, container, false);

        mBinding.setViewModel(mViewModel);

        mAdapter = new CitiesAdapter();
        mBinding.rvCities.setAdapter(mAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subscribeToModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onAttach();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unsubscribeFromModel();
    }

    public void setViewModel(CitiesViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    private void subscribeToModel() {
        mCitiesListChangeCallback = new CitiesListChangeCallback(this);
        mViewModel.data.addOnListChangedCallback(mCitiesListChangeCallback);
    }

    private void unsubscribeFromModel(){
        mViewModel.data.removeOnListChangedCallback(mCitiesListChangeCallback);
        mCitiesListChangeCallback = null;
    }

    /**
     *  Callback for Cities list changes.
     *  Implemented as static inner class to avoid memory leaks.
     */
    private static class CitiesListChangeCallback extends ObservableList.OnListChangedCallback<ObservableList<City>>{

        private WeakReference<CitiesFragment> mFragment;

        public CitiesListChangeCallback(CitiesFragment fragment) {
            this.mFragment = new WeakReference<CitiesFragment>(fragment);
        }

        @Override
        public void onChanged(ObservableList<City> cities) {

        }

        @Override
        public void onItemRangeChanged(ObservableList<City> cities, int i, int i1) {

        }

        @Override
        public void onItemRangeInserted(ObservableList<City> cities, int i, int i1) {
            CitiesFragment fragment = mFragment.get();
            if (fragment!=null && fragment.mAdapter != null) {
                fragment.mAdapter.swapData(cities);
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList<City> cities, int i, int i1, int i2) {

        }

        @Override
        public void onItemRangeRemoved(ObservableList<City> cities, int i, int i1) {

        }
    }
}
