package me.elmira.foursquareclient.data.source.local;

import android.content.Context;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import me.elmira.foursquareclient.data.City;

/**
 * Created by elmira on 11/9/17.
 */

public class LoadFileUtil {

    private static final String FILE_NAME = "cities.json";

    public static List<City> loadCities(Context context) throws Exception {
        InputStream inputStream = context.getAssets().open(FILE_NAME);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        CitiesJsonResponse jsonResponse = new Gson().fromJson(inputStreamReader, CitiesJsonResponse.class);
        return jsonResponse.getCities();
    }
}
