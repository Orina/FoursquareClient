package me.elmira.foursquareclient.cities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.elmira.foursquareclient.data.City;
import me.elmira.foursquareclient.databinding.ItemCityBinding;

/**
 * Created by elmira on 11/8/17.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private List<City> mCities;

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCityBinding binding = ItemCityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        City city = mCities.get(position);
        holder.bind(city);
    }

    public void swapData(List<City> data) {
        if (mCities == null) {
            mCities = new ArrayList<>();
        }
        else {
            mCities.clear();
        }
        if (data != null) {
            mCities.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCities == null ? 0 : mCities.size();
    }
}