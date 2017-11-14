package me.elmira.foursquareclient.venuedetails;

import android.support.v7.widget.RecyclerView;

import java.util.Random;

import me.elmira.foursquareclient.data.VenuePhoto;
import me.elmira.foursquareclient.databinding.ItemVenuePhotoBinding;

/**
 * Created by Elmira on 11/13/2017.
 */

public class VenuePhotoViewHolder extends RecyclerView.ViewHolder {

    private final ItemVenuePhotoBinding mBinding;
    private static Random random = new Random(System.currentTimeMillis());

    public VenuePhotoViewHolder(ItemVenuePhotoBinding binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    public void onBind(VenuePhoto photo) {
        mBinding.setPhoto(photo);
        mBinding.myImageView.setAspectRatio(photo.getAspectRatio());
        mBinding.executePendingBindings();
    }
}
