package me.elmira.foursquareclient.venues;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import me.elmira.foursquareclient.R;
import me.elmira.foursquareclient.ViewModelHolder;

/**
 * Created by Elmira on 11/11/2017.
 */

public class SearchVenuesActivity extends AppCompatActivity {

    private VenuesViewModel mViewModel;
    private static final String SEARCH_VENUES_VIEW_MODEL_TAG = "VenuesVMTag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_venues);

        Intent intent = getIntent();

        SearchVenuesFragment fragment = findOrCreateFragment(
                intent.getStringExtra(SearchVenuesFragment.ARG_LAT_LNG));

        mViewModel = findOrCreateViewModel();
        fragment.setViewModel(mViewModel);

        setTitle(intent.getStringExtra(SearchVenuesFragment.ARG_CITY_NAME));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.onDetach();
        }
    }

    private SearchVenuesFragment findOrCreateFragment(String latLng) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SearchVenuesFragment fragment = (SearchVenuesFragment) fragmentManager.findFragmentById(R.id.searchFrame);

        if (fragment == null) {
            fragment = SearchVenuesFragment.newInstance(latLng);

            fragmentManager.beginTransaction().replace(R.id.searchFrame, fragment).commit();
        }
        return fragment;
    }

    private VenuesViewModel findOrCreateViewModel() {
        ViewModelHolder<VenuesViewModel> vmHolder = (ViewModelHolder<VenuesViewModel>)
                getSupportFragmentManager().findFragmentByTag(SEARCH_VENUES_VIEW_MODEL_TAG);
        if (vmHolder != null && vmHolder.getViewmodel() != null) {
            return vmHolder.getViewmodel();
        }
        // Create ViewModel
        VenuesViewModel viewModel = new VenuesViewModel();

        // Bind ViewModel to this Activity's lifecycle using the Fragment Manager.
        getSupportFragmentManager().beginTransaction()
                .add(ViewModelHolder.createContainer(viewModel), SEARCH_VENUES_VIEW_MODEL_TAG)
                .commit();

        return viewModel;
    }
}
