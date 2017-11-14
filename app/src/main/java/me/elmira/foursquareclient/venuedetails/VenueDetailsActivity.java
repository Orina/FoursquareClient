package me.elmira.foursquareclient.venuedetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import me.elmira.foursquareclient.R;
import me.elmira.foursquareclient.ViewModelHolder;

/**
 * Created by Elmira on 11/13/2017.
 */

public class VenueDetailsActivity extends AppCompatActivity {

    public static final String ARG_VENUE_ID = "venueId";
    private static final String VENUE_DETAILS_VIEW_MODEL_TAG = "VenueDetailsVMTag";
    private VenueDetailsViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_details);

        String venueId = getIntent().getStringExtra(ARG_VENUE_ID);

        mViewModel = findOrCreateViewModel();

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new VenueFragmentPagerAdapter(this, getSupportFragmentManager(), venueId, mViewModel));

        TabLayout tabLayout = findViewById(R.id.slidingTabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private VenueDetailsViewModel findOrCreateViewModel() {
        ViewModelHolder<VenueDetailsViewModel> vmHolder = (ViewModelHolder<VenueDetailsViewModel>) getSupportFragmentManager().findFragmentByTag(VENUE_DETAILS_VIEW_MODEL_TAG);
        if (vmHolder != null && vmHolder.getViewmodel() != null) {
            return vmHolder.getViewmodel();
        }

        VenueDetailsViewModel viewModel = new VenueDetailsViewModel();

        getSupportFragmentManager().beginTransaction()
                .add(ViewModelHolder.createContainer(viewModel), VENUE_DETAILS_VIEW_MODEL_TAG)
                .commit();

        return viewModel;
    }
}
