package me.elmira.foursquareclient.cities;

import android.support.v7.widget.RecyclerView;

import me.elmira.foursquareclient.databinding.ItemCityBinding;
import me.elmira.foursquareclient.model.City;

/**
 * Created by elmira on 11/9/17.
 */

public class CityViewHolder extends RecyclerView.ViewHolder {
    private final ItemCityBinding binding;

    public CityViewHolder(ItemCityBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(City city) {
        binding.setCity(city);
        binding.executePendingBindings();
    }
}
