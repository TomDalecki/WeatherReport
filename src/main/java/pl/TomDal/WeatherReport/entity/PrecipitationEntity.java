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
@Table(name = "precipitation")
public class PrecipitationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "precipitation_id", nullable = false)
    private Long id;

    @Column(name = "total")
    private double total;

    @Column(name = "type")
    private String type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "precipitation")
    private List<WeatherDataEntity> weatherDataEntity;
}
