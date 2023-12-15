package pl.TomDal.WeatherReport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Probability {

    @JsonProperty("precipitation")
    private int precipitation;

    @JsonProperty("storm")
    private double storm;

    @JsonProperty("freeze")
    private int freeze;
}
