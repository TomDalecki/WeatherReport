package pl.TomDal.WeatherReport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherData {

    @JsonProperty("date")
    private LocalDateTime date;

    @JsonProperty("weather")
    private String weather;

    @JsonProperty("icon")
    private int iconType;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("temperature")
    private double temperature;

    @JsonProperty("feels_like")
    private double feelsLike;

    @JsonProperty("wind_chill")
    private double windChill;

    @JsonProperty("dew_point")
    private double dewPoint;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("cloud_cover")
    private int cloudCover;

    @JsonProperty("pressure")
    private int pressure;

    @JsonProperty("precipitation")
    private Precipitation precipitation;

    @JsonProperty("probability")
    private Probability probability;

    @JsonProperty("ozone")
    private double ozone;

    @JsonProperty("uv_index")
    private double uvIndex;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("visibility")
    private double visibility;
}
