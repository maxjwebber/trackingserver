package edu.temple.trackingserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TrackingController {

    TrackingTree dataStore = new TrackingTree();
    @GetMapping("/track-view")
    public TrackingItem trackingRequest(

            @RequestParam(value = "userID") int userID,
            @RequestParam(value = "thisURL") String thisURL,
            @RequestParam(value = "browser") String browser,
            @RequestParam(value = "resolutionX") int resolutionX,
            @RequestParam(value = "resolutionY") int resolutionY,
            HttpServletRequest request)

            throws IOException, ParseException {
        String ip = request.getHeader("X-FORWARDED-FOR");
        LocalDateTime now = LocalDateTime.now();
        TrackingItem newItem = new TrackingItem(userID,thisURL,browser,resolutionX,resolutionY,now,ip);
        dataStore.add(newItem);
        return newItem;
    }
    @GetMapping("/views")
    public TrackingReport viewReport(
            @RequestParam(value = "startDate") String startDateString,
            @RequestParam(value = "endDate") String endDateString)
    {

        if (endDateString.compareTo(startDateString) > 0)
        {
            String temp = endDateString;
            endDateString = startDateString;
            startDateString = temp;
        }

        LocalDateTime startDate = LocalDate.parse(startDateString,DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(endDateString,DateTimeFormatter.ISO_LOCAL_DATE).atTime(23,59,59);

        return dataStore.generateReport(startDate,endDate);
    }
}



