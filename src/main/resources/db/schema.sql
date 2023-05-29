CREATE TABLE IF NOT EXISTS station
(
    station_id        VARCHAR(6)  NOT NULL PRIMARY KEY,
    station_name      VARCHAR(10) NOT NULL,
    station_latitude  VARCHAR(20) NOT NULL,
    station_longitude VARCHAR(20) NOT NULL,
    line_name         VARCHAR(10) NOT NULL,
    station_address   VARCHAR(50) NOT NULL
);
