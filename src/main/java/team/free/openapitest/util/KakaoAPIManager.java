package team.free.openapitest.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import team.free.openapitest.dto.Location;
import team.free.openapitest.dto.LocationDto;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class KakaoAPIManager {

    public static final String KAKAO_API_HOST = "https://dapi.kakao.com";
    public static final String KAKAO_API_KEY_PREFIX = "KakaoAK ";
    public static final String SEARCH_REQUEST_ENDPOINT = "/v2/local/search/keyword.json";

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${kakao.key}")
    private String kakaoAPIKey;

    public Location getStationLocationInfo(String stationName, String lineName) {
        URI uri = getStationLocationRequestUri(stationName + "역 " + lineName);

        LocationDto locationDto = searchLocation(uri);
        assert locationDto != null;

        List<Location> locations = locationDto.getLocations();
        for (Location location : locations) {
            if (location.getName().startsWith(stationName + "역")) {
                return location;
            }
        }

        return null;
    }

    public List<Location> getExitLocationList(String stationName, String lineName) {
        List<Location> exits = new ArrayList<>();

        int exitNumber = 1;
        while (true) {
            String keyword = stationName + "역 " + lineName + " " + exitNumber + "번출구";
            Location location = getLocation(stationName, String.valueOf(exitNumber), keyword);
            if (location == null) {
                break;
            }

            exits.add(location);
            searchExistsSubNumberExit(stationName, lineName, exitNumber, exits);
            exitNumber++;
        }

        return exits;
    }

    private void searchExistsSubNumberExit(String stationName, String lineName, int exitNumber, List<Location> exits) {
        int exitSubNumber = 1;
        while (true) {
            String fullNumber = exitNumber + "-" + exitSubNumber;
            String keyword = stationName + "역 " + lineName + " " + fullNumber + "번출구";
            Location location = getLocation(stationName, fullNumber, keyword);
            if (location == null) {
                break;
            }

            exits.add(location);
            exitSubNumber++;
        }
    }

    private Location getLocation(String stationName, String exitFullNumber, String keyword) {
        URI uri = getExitLocationRequestUri(keyword);
        LocationDto locationDto = searchLocation(uri);
        assert locationDto != null;

        List<Location> locations = locationDto.getLocations();
        if (locations.size() == 0) {
            return null;
        }

        Location location = locations.get(0);
        if (!isSimilarWithKeyword(location.getName(), stationName, exitFullNumber)) {
            return null;
        }
        return location;
    }

    private boolean isSimilarWithKeyword(String name, String stationName, String exitFullNumber) {
        return name.startsWith(stationName + "역") && (name.endsWith(exitFullNumber + "번출구")
                || name.endsWith(exitFullNumber + "번 출구"));
    }

    private LocationDto searchLocation(URI uri) {
        HttpHeaders headers = generateHeader();
        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, uri);
        return restTemplate.exchange(request, LocationDto.class).getBody();
    }

    private URI getStationLocationRequestUri(String keyword) {
        return UriComponentsBuilder.fromUriString(KAKAO_API_HOST + SEARCH_REQUEST_ENDPOINT)
                .queryParam("query", keyword)
                .queryParam("category_group_code", "SW8")
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
    }

    private URI getExitLocationRequestUri(String keyword) {
        return UriComponentsBuilder.fromUriString(KAKAO_API_HOST + SEARCH_REQUEST_ENDPOINT)
                .queryParam("query", keyword)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
    }

    private HttpHeaders generateHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, KAKAO_API_KEY_PREFIX + kakaoAPIKey);
        return headers;
    }
}
