package me.elmira.foursquareclient.venuedetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import static me.elmira.foursquareclient.venuedetails.VenueDetailsActivity.ARG_VENUE_ID;

/**
 * Created by Elmira on 11/13/2017.
 */

public class VenueDetailsFragment extends Fragment {

    private VenueDetailsViewModel mViewModel;

    public VenueDetailsFragment() {
    }

    public static VenueDetailsFragment newInstance(String venueId) {
        VenueDetailsFragment fragment = new VenueDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_VENUE_ID, venueId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setViewModel(VenueDetailsViewModel viewModel) {
        this.mViewModel = viewModel;
    }
}
