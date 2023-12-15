CREATE TABLE precipitation
(
    precipitation_id    SERIAL          NOT NULL,
    total               NUMERIC(8, 2)   NOT NULL,
    type                VARCHAR(30)     NOT NULL,

    PRIMARY KEY (precipitation_id)
);