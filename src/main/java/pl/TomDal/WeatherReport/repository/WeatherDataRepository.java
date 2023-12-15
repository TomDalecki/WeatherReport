package pl.TomDal.WeatherReport.repository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.WeatherReport.model.WeatherData;
import pl.TomDal.WeatherReport.repository.jpa.WeatherDataJpaRepository;
import pl.TomDal.WeatherReport.repository.mapper.WeatherDataEntityMapper;
import pl.TomDal.WeatherReport.service.dao.WeatherDataDAO;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class WeatherDataRepository implements WeatherDataDAO {
    private final WeatherDataJpaRepository weatherDataJpaRepository;
    private final WeatherDataEntityMapper weatherDataEntityMapper;

    @Override
    @Transactional
    public void save(WeatherData weatherData) {
        weatherDataJpaRepository.save(weatherDataEntityMapper.mapToEntity(weatherData));
    }

    @Override
    public List<WeatherData> findAll() {
        return weatherDataJpaRepository.findAll().stream()
                .map(weatherDataEntityMapper::mapFromEntity).toList();
    }

    @Override
    public Optional<WeatherData> findFirst() {
        return Optional.ofNullable(weatherDataEntityMapper.mapFromEntity(weatherDataJpaRepository.findFirst()));
    }

    @Override
    @Transactional
    public void clearData() {
        weatherDataJpaRepository.deleteAll();
    }
}
