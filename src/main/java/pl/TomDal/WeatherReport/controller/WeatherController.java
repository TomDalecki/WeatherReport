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
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static pl.TomDal.WeatherReport.service.WeatherDataService.REFRESH_PERIOD_IN_HOURS;

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
        List<WeatherData> weatherDataList;

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
            System.out.println("--- DANE Z BAZY (AKTUALIZACJA CO " + REFRESH_PERIOD_IN_HOURS + "h)---");
        }

        Map<LocalTime, List<WeatherData>> todayWeatherDataMap1 = weatherDataService
                .prepareThisDayDataFrom0AmTill7AmForView(weatherDataList);

        Map<LocalTime, List<WeatherData>> todayWeatherDataMap2 = weatherDataService
                .prepareThisDayDataFrom8AmTill3PmForView(weatherDataList);

        Map<LocalTime, List<WeatherData>> todayWeatherDataMap3 = weatherDataService
                .prepareThisDayDataFrom4PmTill11PmForView(weatherDataList);

        Map<LocalDate, List<WeatherData>> nextDaysWeatherDataMap = weatherDataService
                .prepareNextDaysDataForView(weatherDataList);

        List<String> hoursList = weatherDataService.createHoursList();

        model.addAttribute("todayWeatherDataMap1", todayWeatherDataMap1);
        model.addAttribute("todayWeatherDataMap2", todayWeatherDataMap2);
        model.addAttribute("todayWeatherDataMap3", todayWeatherDataMap3);
        model.addAttribute("nextDaysWeatherDataMap", nextDaysWeatherDataMap);
        model.addAttribute("hoursList", hoursList);

        return "weather_main_website";
    }
}
