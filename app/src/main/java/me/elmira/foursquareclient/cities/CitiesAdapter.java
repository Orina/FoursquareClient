package me.elmira.foursquareclient.cities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.elmira.foursquareclient.model.City;
import me.elmira.foursquareclient.databinding.ItemCityBinding;

/**
 * Created by elmira on 11/8/17.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private List<City> mCities;
    private final CityClickCallback mCallback;

    public CitiesAdapter(CityClickCallback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCityBinding binding = ItemCityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setCallback(mCallback);
        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        City city = mCities.get(position);
        holder.bind(city);
    }

    public void swapData(List<City> data) {
        mCities = new ArrayList<>(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCities == null ? 0 : mCities.size();
    }
}