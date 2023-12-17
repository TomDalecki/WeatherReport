package pl.TomDal.WeatherReport.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.TomDal.WeatherReport.model.WeatherData;
import pl.TomDal.WeatherReport.service.dao.WeatherDataDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WeatherDataService {
    private final WeatherDataDAO weatherDataDAO;
    public static final int REFRESH_PERIOD_IN_HOURS = 3;

    public void save(List<WeatherData> weatherData) {
        for (WeatherData weatherDatum : weatherData) {
            weatherDataDAO.save(weatherDatum);
        }
    }

    public Optional<WeatherData> findFirst() {
        return weatherDataDAO.findFirst();
    }

    public List<WeatherData> findAll() {
        return weatherDataDAO.findAll();
    }

    public boolean dataValidCheck(WeatherData weatherData) {
        return weatherData.getDate().plusHours(REFRESH_PERIOD_IN_HOURS).isBefore(LocalDateTime.now());
    }

    public void clearData() {
        weatherDataDAO.clearData();
    }

    public Map<LocalTime, List<WeatherData>> prepareThisDayDataFrom0AmTill7AmForView(List<WeatherData> weatherDataList) {

        return weatherDataList.stream()
                .filter(data -> data.getDate().toLocalDate().equals(LocalDate.now()))
                .filter(data -> data.getDate().toLocalTime().isBefore(LocalTime.of(8, 0, 0)))
                .collect(Collectors.toMap(
                        data -> data.getDate().toLocalTime(),
                        data -> new ArrayList<>(Collections.singletonList(data)),
                        (previous, next) -> {
                            previous.addAll(next);
                            return previous;
                        },
                        TreeMap::new));
    }
    public Map<LocalTime, List<WeatherData>> prepareThisDayDataFrom8AmTill3PmForView(List<WeatherData> weatherDataList) {

        return weatherDataList.stream()
                .filter(data -> data.getDate().toLocalDate().equals(LocalDate.now()))
                .filter(data -> data.getDate().toLocalTime().isAfter(LocalTime.of(7, 0, 0)))
                .filter(data -> data.getDate().toLocalTime().isBefore(LocalTime.of(16, 0, 0)))
                .collect(Collectors.toMap(
                        data -> data.getDate().toLocalTime(),
                        data -> new ArrayList<>(Collections.singletonList(data)),
                        (previous, next) -> {
                            previous.addAll(next);
                            return previous;
                        },
                        TreeMap::new));
    }
    public Map<LocalTime, List<WeatherData>> prepareThisDayDataFrom4PmTill11PmForView(List<WeatherData> weatherDataList) {

        return weatherDataList.stream()
                .filter(data -> data.getDate().toLocalDate().equals(LocalDate.now()))
                .filter(data -> data.getDate().toLocalTime().isAfter(LocalTime.of(15, 0, 0)))
                .filter(data -> data.getDate().toLocalTime().isBefore(LocalTime.of(23, 59, 0)))
                .collect(Collectors.toMap(
                        data -> data.getDate().toLocalTime(),
                        data -> new ArrayList<>(Collections.singletonList(data)),
                        (previous, next) -> {
                            previous.addAll(next);
                            return previous;
                        },
                        TreeMap::new));
    }
    public Map<LocalDate, List<WeatherData>> prepareNextDaysDataForView(List<WeatherData> weatherDataList) {

        return weatherDataList.stream()
                .filter(data -> data.getDate().toLocalDate().isAfter(LocalDate.now()))
                .collect(Collectors.toMap(
                        data -> data.getDate().toLocalDate(),
                        data -> new ArrayList<>(Collections.singletonList(data)),
                        (previous, next) -> {
                            previous.addAll(next);
                            return previous;
                        },
                        TreeMap::new));
    }

    public List<String> createHoursList(){
        return List.of("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00",
                "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00",
                "21:00", "22:00", "23:00");
    }

}
