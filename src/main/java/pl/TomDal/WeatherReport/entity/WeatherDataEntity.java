package pl.TomDal.WeatherReport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weather_data")
public class WeatherDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_data_id", nullable = false)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "weather")
    private String weather;
    @Column(name = "icon_type")
    private int iconType;
    @Column(name = "summary")
    private String summary;
    @Column(name = "temperature")
    private double temperature;
    @Column(name = "feels_like")
    private double feelsLike;
    @Column(name = "wind_chill")
    private double windChill;
    @Column(name = "dew_point")
    private double dewPoint;
    @Column(name = "cloud_cover")
    private int cloudCover;
    @Column(name = "pressure")
    private int pressure;
    @Column(name = "ozone")
    private double ozone;
    @Column(name = "uv_index")
    private double uvIndex;
    @Column(name = "humidity")
    private int humidity;
    @Column(name = "visibility")
    private double visibility;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "wind_id")
    private WindEntity wind;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "precipitation_id")
    private PrecipitationEntity precipitation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "probability_id")
    private ProbabilityEntity probability;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hourly_data_id")
    private HourlyDataEntity hourlyData;
}
