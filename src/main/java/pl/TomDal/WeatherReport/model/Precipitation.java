package pl.TomDal.WeatherReport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Precipitation {

    @JsonProperty("total")
    private double total;

    @JsonProperty("type")
    private String type;
}
