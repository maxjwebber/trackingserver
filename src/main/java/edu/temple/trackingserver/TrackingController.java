package edu.temple.trackingserver;
package edu.temple.realestate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class TrackingController {
    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/track-view")
    public Tracking trackingRequest(

            @RequestParam(value = "statistic", defaultValue = "_") String statistic,
            @RequestParam(value = "field", defaultValue = "_") String field,
            @RequestParam(value = "startdate", defaultValue = "01/01/1800") Date startDate,
            @RequestParam(value = "enddate", defaultValue = "12/31/2020") Date endDate,
            @RequestParam(value = "zipcode", defaultValue = "99999") int zipCode)
            throws IOException, ParseException {

        return new Tracking(counter.incrementAndGet(), statistic,field,startDate,endDate,zipCode);
    }
}
