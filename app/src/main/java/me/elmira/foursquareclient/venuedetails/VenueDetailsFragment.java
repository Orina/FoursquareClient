package me.elmira.foursquareclient.venuedetails;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.elmira.foursquareclient.R;
import me.elmira.foursquareclient.databinding.FragmentVenueDetailsBinding;

import static me.elmira.foursquareclient.venuedetails.VenueDetailsActivity.ARG_VENUE_ID;

/**
 * Created by Elmira on 11/13/2017.
 */

public class VenueDetailsFragment extends Fragment implements VenueBookmarkCallback {

    private static final String LOG_TAG = "VenueDetailsFrg";

    private VenueDetailsViewModel mViewModel;
    private String mVenueId;

    private FragmentVenueDetailsBinding mBinding;

    public VenueDetailsFragment() {
    }

    public static VenueDetailsFragment newInstance(String venueId) {
        Log.d(LOG_TAG, "newInstance() venueId: "+venueId);
        VenueDetailsFragment fragment = new VenueDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_VENUE_ID, venueId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        mVenueId = getArguments().getString(ARG_VENUE_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView()");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_venue_details, container, false);
        mBinding.setCallback(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        mViewModel = ((VenueDetailsActivity)getActivity()).getViewModel();
        mBinding.setVenue(mViewModel.venue);
        mViewModel.loadVenue(mVenueId);
    }

    @Override
    public void onVenueBookmark() {
        mViewModel.changeVenueBookmark(mVenueId);
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG_TAG, "onDestroyView()");
        super.onDestroyView();
        if (mBinding!=null) {
            mBinding.unbind();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy()");
        super.onDestroy();
    }
}