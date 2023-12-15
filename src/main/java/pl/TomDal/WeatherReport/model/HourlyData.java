package pl.TomDal.WeatherReport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class HourlyData {

    @JsonProperty("data")
    private List<WeatherData> weatherData;
}
