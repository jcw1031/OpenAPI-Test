package team.free.openapitest.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.free.openapitest.domain.SubwayStation;
import team.free.openapitest.dto.Location;
import team.free.openapitest.repository.SubwayStationRepository;
import team.free.openapitest.util.ExcelReader;
import team.free.openapitest.util.KakaoAPIManager;

import java.io.IOException;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class SubwayStationInfoInitializer {

    public static final int OPERATING_INSTITUTION_INDEX = 0;
    public static final int LINE_ID_INDEX = 2;
    public static final int LINE_NAME_INDEX = 3;
    public static final int STATION_ID_INDEX = 4;
    public static final int STATION_NAME_INDEX = 5;

    private final ExcelReader excelReader;
    private final KakaoAPIManager kakaoAPIManager;
    private final SubwayStationRepository stationRepository;

    /*@PostConstruct
    private void init() throws IOException {
        this.initializeSubwayStationTable();
    }*/

    public void initializeSubwayStationTable() throws IOException {
        Sheet sheet = excelReader.readSheet("/Users/jcw/only2.xlsx");
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            String lineName = row.getCell(LINE_NAME_INDEX).toString();
            String stationName = row.getCell(STATION_NAME_INDEX).toString();
            if (stationName.contains("(")) {
                stationName = getPureName(stationName);
            }

            Location location = kakaoAPIManager.getStationLocationInfo(stationName, lineName);

            SubwayStation subwayStation = SubwayStation.of(stationName, row, location);
            stationRepository.save(subwayStation);
        }
    }

    private static String getPureName(String stationName) {
        int index = stationName.indexOf("(");
        stationName = stationName.substring(0, index);
        return stationName;
    }
}
