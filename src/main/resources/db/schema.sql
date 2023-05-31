CREATE TABLE IF NOT EXISTS station
(
    station_id            VARCHAR(6)  NOT NULL PRIMARY KEY,
    station_name          VARCHAR(10) NOT NULL UNIQUE,
    station_latitude      VARCHAR(20) NOT NULL,
    station_longitude     VARCHAR(20) NOT NULL,
    line_id               VARCHAR(3)  NOT NULL,
    line_name             VARCHAR(10) NOT NULL,
    station_available     BOOLEAN     NOT NULL,
    operating_institution VARCHAR(10) NOT NULL,
    station_address       VARCHAR(50) NOT NULL,
    station_contact       VARCHAR(15),
    UNIQUE (station_latitude, station_longitude)
);

CREATE TABLE IF NOT EXISTS station_exit
(
    exit_id        INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    exit_number    VARCHAR(5)  NOT NULL,
    exit_latitude  VARCHAR(20) NOT NULL,
    exit_longitude VARCHAR(20) NOT NULL,
    station_id     VARCHAR(6)  NOT NULL,
    FOREIGN KEY (station_id) REFERENCES station (station_id),
    UNIQUE (exit_latitude, exit_longitude)
);

CREATE TABLE IF NOT EXISTS elevator
(
    elevator_id        INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    elevator_latitude  VARCHAR(20) NOT NULL,
    elevator_longitude VARCHAR(20) NOT NULL,
    elevator_status    VARCHAR(10),
    description        VARCHAR(50),
    station_id         VARCHAR(6)  NOT NULL,
    FOREIGN KEY (station_id) REFERENCES station (station_id),
    UNIQUE (elevator_latitude, elevator_longitude)
);
