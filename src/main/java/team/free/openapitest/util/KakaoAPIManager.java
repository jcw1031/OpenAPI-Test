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
import java.util.List;

@Slf4j
@Component
public class KakaoAPIManager {

    public static final String KAKAO_API_PREFIX = "https://dapi.kakao.com";
    public static final String KAKAO_API_KEY_PREFIX = "KakaoAK ";

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${kakao.key}")
    private String kakaoAPIKey;

    public Location getStationLocationInfo(String stationName, String lineName) {
        String url = KAKAO_API_PREFIX + "/v2/local/search/keyword.json";
        HttpHeaders headers = generateHeader();
        URI uri = generateURI(stationName, lineName, url);

        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, uri);
        LocationDto locationDto = restTemplate.exchange(request, LocationDto.class).getBody();

        assert locationDto != null;
        List<Location> locations = locationDto.getLocations();
        for (Location location : locations) {
            if (location.getName().startsWith(stationName + "역")) {
                return location;
            }
        }

        return null;
    }

    private URI generateURI(String stationName, String lineName, String url) {
        return UriComponentsBuilder.fromUriString(url)
                .queryParam("query", stationName + " " + lineName)
                .queryParam("category_group_code", "SW8")
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
