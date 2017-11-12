package me.elmira.foursquareclient.cities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import me.elmira.foursquareclient.R;
import me.elmira.foursquareclient.ViewModelHolder;

public class CitiesActivity extends AppCompatActivity {

    private static final String LOG_TAG = "CitiesActivity";
    private static final String CITIES_VIEW_MODEL_TAG = "CitiesVMTag";

    CitiesViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        CitiesFragment fragment = findOrCreateFragment();

        mViewModel = findOrCreateViewModel();

        // Link View and ViewModel
        fragment.setViewModel(mViewModel);
    }

    private CitiesFragment findOrCreateFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        CitiesFragment fragment = (CitiesFragment) fragmentManager.findFragmentById(R.id.contentFrame);

        if (fragment == null) {
            fragment = CitiesFragment.newInstance();

            fragmentManager.beginTransaction()
                    .replace(R.id.contentFrame, fragment)
                    .addToBackStack("cities")
                    .commit();
        }
        return fragment;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mViewModel!=null){
            mViewModel.onDetach();
        }
    }

    private CitiesViewModel findOrCreateViewModel() {
        ViewModelHolder<CitiesViewModel> vmHolder = (ViewModelHolder<CitiesViewModel>) getSupportFragmentManager().findFragmentByTag(CITIES_VIEW_MODEL_TAG);
        if (vmHolder != null && vmHolder.getViewmodel() != null) {
            return vmHolder.getViewmodel();
        }
        // Create ViewModel
        CitiesViewModel viewModel = new CitiesViewModel(new WeakReference<Context>(getApplicationContext()));

        // Bind ViewModel to this Activity's lifecycle using the Fragment Manager.
        getSupportFragmentManager().beginTransaction()
                .add(ViewModelHolder.createContainer(viewModel), CITIES_VIEW_MODEL_TAG)
                .commit();

        return viewModel;
    }
}