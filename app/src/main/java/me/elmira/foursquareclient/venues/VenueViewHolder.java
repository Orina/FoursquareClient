package me.elmira.foursquareclient.venues;

import android.support.v7.widget.RecyclerView;

import me.elmira.foursquareclient.databinding.ItemVenueBinding;
import me.elmira.foursquareclient.model.Venue;

/**
 * Created by elmira on 11/9/17.
 */

public class VenueViewHolder extends RecyclerView.ViewHolder {

    private final ItemVenueBinding binding;

    public VenueViewHolder(ItemVenueBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void onBind(Venue venue) {
        binding.setVenue(venue);
        binding.executePendingBindings();
    }
}
