package pl.TomDal.WeatherReport.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.WeatherReport.entity.WeatherDataEntity;
import pl.TomDal.WeatherReport.model.WeatherData;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WeatherDataEntityMapper {

    WeatherData mapFromEntity(WeatherDataEntity entity);

    WeatherDataEntity mapToEntity(WeatherData weatherData);
}
