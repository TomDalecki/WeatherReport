package pl.TomDal.WeatherReport.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weather_response")
public class WeatherResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_response_id", nullable = false)
    private Long id;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "elevation")
    private int elevation;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "units")
    private String units;

    @ManyToOne
    @JoinColumn(name = "hourly_data_id")
    private HourlyDataEntity hourlyData;
}
