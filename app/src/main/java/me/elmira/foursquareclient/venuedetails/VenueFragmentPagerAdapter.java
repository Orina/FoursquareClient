package me.elmira.foursquareclient.venuedetails;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.lang.ref.WeakReference;

import me.elmira.foursquareclient.R;

/**
 * Created by Elmira on 11/13/2017.
 */

public class VenueFragmentPagerAdapter extends FragmentPagerAdapter {
    private final static int PAGE_COUNT = 2;
    private final static int DETAILS_TAB_POSITION = 1;
    private final static int PHOTOS_TAB_POSITION = 0;

    private WeakReference<Context> mContext;

    final String mVenueId;
    private VenueDetailsViewModel mViewModel;

    public VenueFragmentPagerAdapter(Context cnt, FragmentManager fm, String venueId, VenueDetailsViewModel viewModel) {
        super(fm);
        mContext = new WeakReference<>(cnt);
        this.mVenueId = venueId;
        this.mViewModel = viewModel;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == DETAILS_TAB_POSITION) {
            VenueDetailsFragment fragment = VenueDetailsFragment.newInstance(mVenueId);
            fragment.setViewModel(mViewModel);
            return fragment;

        } else if (position == PHOTOS_TAB_POSITION) {
            VenuePhotosFragment fragment = VenuePhotosFragment.newInstance(mVenueId);
            fragment.setViewModel(mViewModel);
            return fragment;
        } else return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Context cnt = mContext.get();
        if (cnt == null) return null;

        if (position == DETAILS_TAB_POSITION) {
            return cnt.getResources().getString(R.string.tab_info);
        } else if (position == PHOTOS_TAB_POSITION) {
            return cnt.getResources().getString(R.string.tab_photos);
        }
        return null;
    }
}
