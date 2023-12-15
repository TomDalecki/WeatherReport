CREATE TABLE weather_response
(
    weather_response_id     SERIAL                  NOT NULL,
    latitude                VARCHAR(30)             NOT NULL,
    longitude               VARCHAR(30)             NOT NULL,
    elevation               INT                     NOT NULL,
    timezone                VARCHAR(30)             NOT NULL,
    units                   VARCHAR(30)             NOT NULL,
    hourly_data_id          INT                     NOT NULL,

    PRIMARY KEY (weather_response_id),

    CONSTRAINT fk_weather_response_hourly_data
        FOREIGN KEY (hourly_data_id)
            REFERENCES hourly_data (hourly_data_id)
);