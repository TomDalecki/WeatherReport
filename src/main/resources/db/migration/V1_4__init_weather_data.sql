CREATE TABLE weather_data
(
    weather_data_id     SERIAL                      NOT NULL,
    date                TIMESTAMP WITH TIME ZONE    NOT NULL,
    weather             VARCHAR(30)                 NOT NULL,
    icon_type           INT                         NOT NULL,
    summary             VARCHAR(30)                 NOT NULL,
    temperature         NUMERIC(8, 2)               NOT NULL,
    feels_like          NUMERIC(8, 2)               NOT NULL,
    wind_chill          NUMERIC(8, 2)               NOT NULL,
    dew_point           NUMERIC(8, 2)               NOT NULL,
    cloud_cover         INT                         NOT NULL,
    pressure            INT                         NOT NULL,
    ozone               NUMERIC(8, 2)               NOT NULL,
    uv_index            NUMERIC(8, 2)               NOT NULL,
    humidity            INT                         NOT NULL,
    visibility          NUMERIC(8, 2)               NOT NULL,
    wind_id             INT                         NOT NULL,
    precipitation_id    INT                         NOT NULL,
    probability_id      INT                         NOT NULL,
    hourly_data_id      INT                                 ,

    PRIMARY KEY (weather_data_id),

    CONSTRAINT fk_weather_data_wind
        FOREIGN KEY (wind_id)
            REFERENCES wind (wind_id),
    CONSTRAINT fk_weather_data_precipitation
        FOREIGN KEY (precipitation_id)
            REFERENCES precipitation (precipitation_id),
    CONSTRAINT fk_weather_data_probability
        FOREIGN KEY (probability_id)
            REFERENCES probability (probability_id),
    CONSTRAINT fk_weather_data_hourly_data
        FOREIGN KEY (hourly_data_id)
            REFERENCES hourly_data (hourly_data_id)
);