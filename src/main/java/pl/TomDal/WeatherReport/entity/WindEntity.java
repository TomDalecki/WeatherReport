package pl.TomDal.WeatherReport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wind")
public class WindEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wind_id", nullable = false)
    private Long id;

    @Column(name = "speed")
    private double speed;

    @Column(name = "gusts")
    private double gusts;

    @Column(name = "dir")
    private String dir;

    @Column(name = "angle")
    private int angle;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "wind")
    private List<WeatherDataEntity> weatherDataEntity;
}
