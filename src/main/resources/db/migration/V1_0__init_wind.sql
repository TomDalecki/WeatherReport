CREATE TABLE wind
(
    wind_id     SERIAL                  NOT NULL,
    speed       NUMERIC(8, 2)           NOT NULL,
    gusts       NUMERIC(8, 2)           NOT NULL,
    dir         VARCHAR(30)             NOT NULL,
    angle       INT                     NOT NULL,

    PRIMARY KEY (wind_id)
);