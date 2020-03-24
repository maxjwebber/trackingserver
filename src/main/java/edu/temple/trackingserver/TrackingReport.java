package edu.temple.trackingserver;

public class TrackingReport
{
    int numUniques;
    String[] mostViewedURLs;
    String[] mostUsedBrowsers;
    int[] numRequestsPerMin;

    public TrackingReport(int numUniques,String[] mostViewedURLs,String[] mostUsedBrowsers,int[] numRequestsPerMin)
    {
        this.numUniques=numUniques;
        this.mostUsedBrowsers=mostUsedBrowsers;
        this.mostViewedURLs=mostViewedURLs;
        this.numRequestsPerMin = numRequestsPerMin;
    }
}
