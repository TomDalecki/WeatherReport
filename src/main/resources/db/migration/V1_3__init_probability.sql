CREATE TABLE probability
(
    probability_id  SERIAL          NOT NULL,
    precipitation   INT             NOT NULL,
    storm           NUMERIC(8, 2)   NOT NULL,
    freeze_1        INT             NOT NULL,
    PRIMARY KEY (probability_id)
);