package team.free.openapitest.util;

import org.springframework.stereotype.Component;

@Component
public class GeographicalDistanceUtils {

    private static final double METER_PER_MILE = 1609.34;

    /**
     * @Unit: Meter
     */
    public static double calculateDistance(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
        int earthRadius = 6371;

        double dLat = Math.toRadians(latitudeB - latitudeA);
        double dLon = Math.toRadians(longitudeB - longitudeA);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(latitudeA)) * Math.cos(Math.toRadians(latitudeB)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = earthRadius * c;

        return distance * 1000;
    }
}
