package me.elmira.foursquareclient.venuedetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.elmira.foursquareclient.databinding.ItemVenuePhotoBinding;
import me.elmira.foursquareclient.model.VenuePhoto;

/**
 * Created by Elmira on 11/13/2017.
 */

public class VenuePhotosAdapter extends RecyclerView.Adapter<VenuePhotoViewHolder> {
    private List<VenuePhoto> mPhotos;

    @Override
    public VenuePhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemVenuePhotoBinding binding = ItemVenuePhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VenuePhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(VenuePhotoViewHolder holder, int position) {
        holder.onBind(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotos == null ? 0 : mPhotos.size();
    }

    public void swapData(List<VenuePhoto> data) {
        mPhotos = new ArrayList<>(data);
        notifyDataSetChanged();
    }
}
