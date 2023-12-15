package pl.TomDal.WeatherReport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse {
    @JsonProperty("lat")
    private String latitude;

    @JsonProperty("lon")
    private String longitude;

    @JsonProperty("elevation")
    private int elevation;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("units")
    private String units;

    @JsonProperty("hourly")
    private HourlyData hourly;
}
