package pl.TomDal.WeatherReport.dao;

import pl.TomDal.WeatherReport.model.WeatherData;

import java.util.List;

public interface WeatherDto {
     List<WeatherData> getWeatherData(String place);
}
