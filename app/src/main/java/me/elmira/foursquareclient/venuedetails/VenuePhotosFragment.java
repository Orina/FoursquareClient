package me.elmira.foursquareclient.venuedetails;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import me.elmira.foursquareclient.R;
import me.elmira.foursquareclient.databinding.FragmentVenuePhotosBinding;
import me.elmira.foursquareclient.model.VenuePhoto;
import me.elmira.foursquareclient.util.ItemOffsetDecoration;

import static me.elmira.foursquareclient.venuedetails.VenueDetailsActivity.ARG_VENUE_ID;

/**
 * Created by Elmira on 11/13/2017.
 */

public class VenuePhotosFragment extends Fragment {

    private FragmentVenuePhotosBinding mBinding;
    private VenuePhotosAdapter mAdapter;

    private VenueDetailsViewModel mViewModel;
    private VenuePhotosCallback mPhotosCallback;

    private String mVenueId;

    public VenuePhotosFragment() {
    }

    public static VenuePhotosFragment newInstance(String venueId) {
        VenuePhotosFragment fragment = new VenuePhotosFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_VENUE_ID, venueId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVenueId = getArguments().getString(ARG_VENUE_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_venue_photos, container, false);
        setupRecyclerView();
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ((VenueDetailsActivity) getActivity()).getViewModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        subscribeToModel();
        mViewModel.loadVenuePhotos(mVenueId);
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

    private void setupRecyclerView() {
        mAdapter = new VenuePhotosAdapter();
        mBinding.rvPhotos.setAdapter(mAdapter);

        mBinding.rvPhotos.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        mBinding.rvPhotos.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvPhotos.addItemDecoration(new ItemOffsetDecoration(getResources().getDimensionPixelSize(R.dimen.margin_small)));
    }

    private void subscribeToModel() {
        mPhotosCallback = new VenuePhotosCallback(this);
        if (mViewModel != null) {
            mViewModel.photos.addOnListChangedCallback(mPhotosCallback);
        }
    }

    private void unsubscribeFromModel() {
        if (mViewModel != null) {
            mViewModel.photos.removeOnListChangedCallback(mPhotosCallback);
        }
    }

    private static class VenuePhotosCallback extends ObservableList.OnListChangedCallback<ObservableList<VenuePhoto>> {
        private final WeakReference<VenuePhotosFragment> mFragment;

        public VenuePhotosCallback(VenuePhotosFragment fragment) {
            this.mFragment = new WeakReference<VenuePhotosFragment>(fragment);
        }

        @Override
        public void onChanged(ObservableList<VenuePhoto> venuePhotos) {

        }

        @Override
        public void onItemRangeChanged(ObservableList<VenuePhoto> venuePhotos, int i, int i1) {

        }

        @Override
        public void onItemRangeInserted(ObservableList<VenuePhoto> venuePhotos, int i, int i1) {
            VenuePhotosFragment fragment = mFragment.get();
            if (fragment != null) {
                fragment.mAdapter.swapData(venuePhotos.subList(0, venuePhotos.size()));
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList<VenuePhoto> venuePhotos, int i, int i1, int i2) {

        }

        @Override
        public void onItemRangeRemoved(ObservableList<VenuePhoto> venuePhotos, int i, int i1) {

        }
    }
}
