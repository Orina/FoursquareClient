package me.elmira.foursquareclient.venues;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.elmira.foursquareclient.model.Venue;
import me.elmira.foursquareclient.databinding.ItemVenueBinding;

/**
 * Created by elmira on 11/9/17.
 */

public class VenueAdapter extends RecyclerView.Adapter<VenueViewHolder> {

    private List<Venue> mVenues;

    private final VenueClickCallback mVenueClickCallback;

    public VenueAdapter(VenueClickCallback mVenueClickCallback) {
        this.mVenueClickCallback = mVenueClickCallback;
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemVenueBinding binding = ItemVenueBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setCallback(mVenueClickCallback);
        return new VenueViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        Venue venue = mVenues.get(position);
        holder.onBind(venue);
    }

    @Override
    public int getItemCount() {
        return mVenues == null ? 0 : mVenues.size();
    }

    public void swapData(List<Venue> data) {
        if (mVenues == null) mVenues = new ArrayList<>();
        if (data != null && data.size() > 0) mVenues.addAll(data);
        notifyDataSetChanged();
    }
}