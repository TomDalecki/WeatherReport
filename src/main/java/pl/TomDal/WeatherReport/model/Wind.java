package pl.TomDal.WeatherReport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Wind {
    @JsonProperty("speed")
    private double speed;

    @JsonProperty("gusts")
    private double gusts;

    @JsonProperty("dir")
    private String dir;

    @JsonProperty("angle")
    private int angle;
}
