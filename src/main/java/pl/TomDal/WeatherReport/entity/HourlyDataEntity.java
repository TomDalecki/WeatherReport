package pl.TomDal.WeatherReport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hourly_data")
public class HourlyDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hourly_data_id", nullable = false)
    private Long hourlyDataId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hourlyData")
    private List<WeatherDataEntity> weatherData;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hourlyData")
    private List<WeatherResponseEntity> weatherResponse;
}
