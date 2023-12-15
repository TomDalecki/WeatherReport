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
@Table(name = "probability")
public class ProbabilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "probability_id", nullable = false)
    private Long id;

    @Column(name = "precipitation")
    private int precipitation;

    @Column(name = "storm")
    private double storm;

    @Column(name = "freeze_1")
    private int freeze;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "probability")
    private List<WeatherDataEntity> weatherDataEntity;
}
