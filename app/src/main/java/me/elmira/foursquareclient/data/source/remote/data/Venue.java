package me.elmira.foursquareclient.data.source.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venue {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("verified")
    @Expose
    private boolean verified;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("rating")
    @Expose
    private double rating;
    @SerializedName("ratingColor")
    @Expose
    private String ratingColor;
    @SerializedName("ratingSignals")
    @Expose
    private int ratingSignals;
    @SerializedName("allowMenuUrlEdit")
    @Expose
    private boolean allowMenuUrlEdit;
    @SerializedName("beenHere")
    @Expose
    private BeenHere beenHere;
    @SerializedName("hours")
    @Expose
    private Hours hours;
    @SerializedName("events")
    @Expose
    private Events events;
    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("storeId")
    @Expose
    private String storeId;
    @SerializedName("hereNow")
    @Expose
    private HereNow hereNow;
    @SerializedName("featuredPhotos")
    @Expose
    private FeaturedPhotos featuredPhotos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public int getRatingSignals() {
        return ratingSignals;
    }

    public void setRatingSignals(int ratingSignals) {
        this.ratingSignals = ratingSignals;
    }

    public boolean isAllowMenuUrlEdit() {
        return allowMenuUrlEdit;
    }

    public void setAllowMenuUrlEdit(boolean allowMenuUrlEdit) {
        this.allowMenuUrlEdit = allowMenuUrlEdit;
    }

    public BeenHere getBeenHere() {
        return beenHere;
    }

    public void setBeenHere(BeenHere beenHere) {
        this.beenHere = beenHere;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public HereNow getHereNow() {
        return hereNow;
    }

    public void setHereNow(HereNow hereNow) {
        this.hereNow = hereNow;
    }

    public FeaturedPhotos getFeaturedPhotos() {
        return featuredPhotos;
    }

    public void setFeaturedPhotos(FeaturedPhotos featuredPhotos) {
        this.featuredPhotos = featuredPhotos;
    }

}
