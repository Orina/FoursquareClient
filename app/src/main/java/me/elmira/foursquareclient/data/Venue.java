package me.elmira.foursquareclient.data;

import java.util.List;

import me.elmira.foursquareclient.data.source.remote.data.Category;
import me.elmira.foursquareclient.data.source.remote.data.Item__;
import me.elmira.foursquareclient.data.source.remote.data.Tip;

/**
 * Created by elmira on 11/8/17.
 */

public class Venue {

    private static final String THUMBNAIL_SIZE = "100x100";
    private String id;
    private String name;
    private String imageUrl;
    private String address;
    private String categories;
    private String tips;

    public Venue(me.elmira.foursquareclient.data.source.remote.data.Venue gsonVenue, List<Tip> tipList) {
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
}
