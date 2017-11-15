package me.elmira.foursquareclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import me.elmira.foursquareclient.model.source.remote.data.Category;
import me.elmira.foursquareclient.model.source.remote.data.Item__;
import me.elmira.foursquareclient.model.source.remote.data.Tip;

/**
 * Created by Elmira on 11/8/17.
 */

public class Venue implements Parcelable {

    public static final Parcelable.Creator<Venue> CREATOR = new Parcelable.Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel source) {
            return new Venue(source);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };
    private static final String THUMBNAIL_SIZE = "300x300";
    private String id;
    private String name;
    private String imageUrl;
    private String address;
    private String categories;
    private String tips;
    private boolean bookmarked;
    private String rating;
    private String ratingColor;

    public Venue(me.elmira.foursquareclient.model.source.remote.data.Venue gsonVenue, List<Tip> tipList) {
        this.id = gsonVenue.getId();
        this.name = gsonVenue.getName();

        StringBuilder sb = new StringBuilder();
        if (gsonVenue.getLocation() != null && gsonVenue.getLocation().getFormattedAddress() != null) {
            for (String formatted : gsonVenue.getLocation().getFormattedAddress()) {
                sb.append(formatted).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            this.address = sb.toString();
            sb.delete(0, sb.length());
        }

        if (gsonVenue.getCategories() != null) {
            for (Category category : gsonVenue.getCategories()) {
                sb.append(category.getName()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            this.categories = sb.toString();
            sb.delete(0, sb.length());
        }
        if (gsonVenue.getPhotos() != null && gsonVenue.getPhotos().getGroups().size() > 0) {
            Item__ photo = gsonVenue.getPhotos().getGroups().get(0).getItems().get(0);
            imageUrl = photo.getPrefix() + THUMBNAIL_SIZE + photo.getSuffix();
        }
        if (tipList != null && tipList.size() > 0) {
            this.tips = tipList.get(0).getText();
        }
        this.rating = "" + gsonVenue.getRating();
        this.ratingColor = gsonVenue.getRatingColor();
    }

    protected Venue(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.imageUrl = in.readString();
        this.address = in.readString();
        this.categories = in.readString();
        this.tips = in.readString();
        this.bookmarked = in.readByte() != 0;
        this.rating = in.readString();
        this.ratingColor = in.readString();
    }

    public String getTips() {
        return tips;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategories() {
        return categories;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public String getRating() {
        return rating;
    }

    public String getRatingColor() {
        return "#" + ratingColor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
        dest.writeString(this.address);
        dest.writeString(this.categories);
        dest.writeString(this.tips);
        dest.writeByte(this.bookmarked ? (byte) 1 : (byte) 0);
        dest.writeString(this.rating);
        dest.writeString(this.ratingColor);
    }
}
