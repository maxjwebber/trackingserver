package edu.temple.trackingserver;

import java.time.LocalDateTime;

public class TrackingItem
{

    int userID;
    String thisURL;
    String browser;
    int resolutionX;
    int resolutionY;
    LocalDateTime date;
    String ip;

    TrackingItem left;
    TrackingItem right;

    public TrackingItem(int userID, String thisURL, String browser, int resolutionX, int resolutionY, LocalDateTime date, String ip)
    {
        this.userID=userID;
        this.thisURL=thisURL;
        this.browser=browser;
        this.resolutionX=resolutionX;
        this.resolutionY=resolutionY;
        this.date=date;
        this.ip=ip;
    }

    public int getUserID() {
        return userID;
    }

    public String getThisURL() {
        return thisURL;
    }

    public String getBrowser() {
        return browser;
    }

    public int getResolutionX() {
        return resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
