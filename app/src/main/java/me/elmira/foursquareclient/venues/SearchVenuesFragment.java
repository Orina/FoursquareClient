package me.elmira.foursquareclient.venues;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import me.elmira.foursquareclient.R;
import me.elmira.foursquareclient.databinding.FragmentSearchVenuesBinding;
import me.elmira.foursquareclient.model.Venue;
import me.elmira.foursquareclient.venuedetails.VenueDetailsActivity;

/**
 * Created by Elmira Andreeva on 11/11/2017.
 */

public class SearchVenuesFragment extends Fragment implements VenueClickCallback {
    public static final String ARG_LAT_LNG = "argLatLng";
    public static final String ARG_CITY_NAME = "argCityName";

    private VenuesViewModel mViewModel;
    private FragmentSearchVenuesBinding mBinding;

    private VenueAdapter mAdapter;

    private String mLatLng;

    private VenueChangeCallback mVenueChangeCallback;

    public SearchVenuesFragment() {
    }

    public static SearchVenuesFragment newInstance(String latLng) {
        SearchVenuesFragment fragment = new SearchVenuesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_LAT_LNG, latLng);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLatLng = getArguments().getString(ARG_LAT_LNG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_venues, container, false);
        setupRecyclerView();
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
        mViewModel.searchVenues(mLatLng);
        mBinding.setLoading(mViewModel.dataLoading);
        mBinding.setEmptyData(mViewModel.emptyData);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBinding != null) {
            mBinding.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unsubscribeFromModel();
    }

    public void setViewModel(VenuesViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    private void setupRecyclerView() {
        mAdapter = new VenueAdapter(this);

        mBinding.rvVenues.setAdapter(mAdapter);
        mBinding.rvVenues.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mBinding.rvVenues.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvVenues.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onClick(Venue venue) {
        Intent intent = new Intent(this.getContext(), VenueDetailsActivity.class);
        intent.putExtra(VenueDetailsActivity.ARG_VENUE_ID, venue.getId());
        startActivity(intent);
    }

    private void subscribeToModel() {
        mVenueChangeCallback = new VenueChangeCallback(this);
        mViewModel.items.addOnListChangedCallback(mVenueChangeCallback);
    }

    private void unsubscribeFromModel() {
        mViewModel.items.removeOnListChangedCallback(mVenueChangeCallback);
    }

    private static class VenueChangeCallback extends ObservableList.OnListChangedCallback<ObservableList<Venue>> {
        private WeakReference<SearchVenuesFragment> mFragment;

        VenueChangeCallback(SearchVenuesFragment fragment) {
            mFragment = new WeakReference<SearchVenuesFragment>(fragment);
        }

        @Override
        public void onChanged(ObservableList<Venue> venues) {

        }

        @Override
        public void onItemRangeChanged(ObservableList<Venue> venues, int i, int i1) {

        }

        @Override
        public void onItemRangeInserted(ObservableList<Venue> venues, int i, int i1) {
            SearchVenuesFragment fragment = mFragment.get();
            if (fragment != null) {
                fragment.mAdapter.swapData(venues);
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList<Venue> venues, int i, int i1, int i2) {

        }

        @Override
        public void onItemRangeRemoved(ObservableList<Venue> venues, int i, int i1) {

        }
    }
}
