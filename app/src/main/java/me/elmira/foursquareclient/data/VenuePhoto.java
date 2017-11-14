package me.elmira.foursquareclient.data;

/**
 * Created by elmira on 11/10/17.
 */

public class VenuePhoto {

    private String imageUrl;
    private float aspectRatio;

    public VenuePhoto(String imageUrl, float aspectRatio) {
        this.imageUrl = imageUrl;
        this.aspectRatio = aspectRatio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }
}
