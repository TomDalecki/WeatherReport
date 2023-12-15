package pl.TomDal.WeatherReport.service.dao;

import pl.TomDal.WeatherReport.model.WeatherData;

import java.util.List;
import java.util.Optional;

public interface WeatherDataDAO {
    void save(WeatherData weatherData);

    List<WeatherData> findAll();

    Optional<WeatherData> findFirst();

    void clearData();
}
