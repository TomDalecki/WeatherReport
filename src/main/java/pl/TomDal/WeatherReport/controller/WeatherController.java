package pl.TomDal.WeatherReport.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.TomDal.WeatherReport.model.WeatherData;
import pl.TomDal.WeatherReport.service.WeatherDataService;
import pl.TomDal.WeatherReport.weatherClient.WeatherClientImp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherClientImp weatherClientImp;
    private final WeatherDataService weatherDataService;

    @GetMapping("/{place}")
    public String getWeatherForecastDataByPlaceWithComplexRequest(@PathVariable String place, Model model) {
        boolean dataValidCheck = true;
        Optional<WeatherData> weatherData = weatherDataService.findFirst();
        List<WeatherData> weatherDataList = new ArrayList<>();

        if (weatherData.isPresent()) {
            dataValidCheck = weatherDataService.dataValidCheck(weatherData.get());
        }

        if (dataValidCheck) {
            weatherDataService.clearData();
            weatherDataList = weatherClientImp.getWeatherData(place);
            weatherDataService.save(weatherDataList);
        } else {
            weatherDataList = weatherDataService.findAll();
            System.out.println();
            System.out.println("--- DANE Z BAZY (AKTUALIZACJA CO 4h)---");
        }

        Map<LocalTime, List<WeatherData>> todayWeatherDataMap = weatherDataService.prepareThisDayDataForView(weatherDataList);
        Map<LocalDate, List<WeatherData>> nextDaysWeatherDataMap = weatherDataService.prepareNextDaysDataForView(weatherDataList);
        List<String> hoursList = weatherDataService.createHoursList();

        model.addAttribute("todayWeatherDataMap", todayWeatherDataMap);
        model.addAttribute("nextDaysWeatherDataMap", nextDaysWeatherDataMap);
        model.addAttribute("hoursList", hoursList);

        return "weather_main_website";
    }
}
