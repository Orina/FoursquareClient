package me.elmira.foursquareclient.data.source.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Likes {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("groups")
    @Expose
    private List<Object> groups = null;
    @SerializedName("summary")
    @Expose
    private String summary;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
