package edu.temple.trackingserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
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
        dataStore.add(new TrackingItem(userID,thisURL,browser,resolutionX,resolutionY,now,ip));
        return new TrackingItem(userID,thisURL,browser,resolutionX,resolutionY,now,ip);
    }
}


