package pl.TomDal.WeatherReport.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.TomDal.WeatherReport.entity.WeatherDataEntity;

@Repository
public interface WeatherDataJpaRepository extends JpaRepository<WeatherDataEntity, Long> {

    @Query("select w from WeatherDataEntity w order by w.date limit 1")
    WeatherDataEntity findFirst();
}
