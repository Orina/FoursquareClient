package me.elmira.foursquareclient.data.source.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hours {

    @SerializedName("isOpen")
    @Expose
    private boolean isOpen;
    @SerializedName("isLocalHoliday")
    @Expose
    private boolean isLocalHoliday;

    public boolean isIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isIsLocalHoliday() {
        return isLocalHoliday;
    }

    public void setIsLocalHoliday(boolean isLocalHoliday) {
        this.isLocalHoliday = isLocalHoliday;
    }

}
