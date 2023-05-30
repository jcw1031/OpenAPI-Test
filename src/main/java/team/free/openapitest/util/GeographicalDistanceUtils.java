package team.free.openapitest.util;

import org.springframework.stereotype.Component;

@Component
public class GeographicalDistanceUtils {

    private static final double METER_PER_MILE = 1609.34;

    /**
     * @Unit: Meter
     */
    public double distance(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
        if (latitudeA == latitudeB && longitudeA == longitudeB) {
            return 0;
        }

        double theta = longitudeA - longitudeB;
        double distance = Math.sin(Math.toRadians(latitudeA)) * Math.sin(Math.toRadians(latitudeB))
                + Math.cos(Math.toRadians(latitudeA)) * Math.cos(Math.toRadians(latitudeB))
                * Math.cos(Math.toRadians(theta));
        return distance * 60 * 1.1515 * METER_PER_MILE;
    }
}
