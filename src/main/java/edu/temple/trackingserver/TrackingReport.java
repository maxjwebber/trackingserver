package edu.temple.trackingserver;

public class TrackingReport
{
    public int getNumUniques() {
        return numUniques;
    }

    public String[] getMostViewedURLs() {
        return mostViewedURLs;
    }

    public String[] getMostUsedBrowsers() {
        return mostUsedBrowsers;
    }

    public int[] getNumRequestsPerMin() {
        return numRequestsPerMin;
    }

    public int numUniques;
    public String[] mostViewedURLs;
    public String[] mostUsedBrowsers;
    public int[] numRequestsPerMin;

    public TrackingReport(int numUniques,String[] mostViewedURLs,String[] mostUsedBrowsers,int[] numRequestsPerMin)
    {
        this.numUniques=numUniques;
        this.mostUsedBrowsers=mostUsedBrowsers;
        this.mostViewedURLs=mostViewedURLs;
        this.numRequestsPerMin = numRequestsPerMin;
    }
}
