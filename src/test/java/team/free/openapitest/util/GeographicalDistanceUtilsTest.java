package team.free.openapitest.util;

import org.junit.jupiter.api.Test;

class GeographicalDistanceUtilsTest {

    private GeographicalDistanceUtils geographicalDistanceUtils = new GeographicalDistanceUtils();

    @Test
    void distanceTest() throws Exception {
        double distance = geographicalDistanceUtils.calculateDistance(37.56368183746611, 126.97559827045151,
                37.563264360152935, 126.9741145489079);
        System.out.println("distance = " + distance);
    }

    @Test
    void splitTest() throws Exception {
        String name = "시청역 2호선 1번출구";
        String exitNumber = name.split(" ")[2];
        String split = exitNumber.split("번출구")[0];
        System.out.println("split = " + split);
    }
}