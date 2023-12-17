package pl.TomDal.WeatherReport.weatherClient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.TomDal.WeatherReport.dao.WeatherDto;
import pl.TomDal.WeatherReport.model.WeatherData;
import pl.TomDal.WeatherReport.model.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class WeatherClientImp implements WeatherDto {
    private final WebClient webClient;

    @Override
    public List<WeatherData> getWeatherData(String place) {
        List<WeatherData> weatherDataList = new ArrayList<>();

        try {
            WeatherResponse response = webClient
                    .get()
                    .uri("hourly?place_id=" + place + "&timezone=auto&language=pl&units=auto")
                    .retrieve()
                    .bodyToMono(WeatherResponse.class)
                    .block();


            if (response != null && response.getHourly() != null && response.getHourly().getWeatherData() != null) {
                weatherDataList = response.getHourly().getWeatherData();

                // ewentualna dodatkowa logika przetwarzania danych
            }

            System.out.println();
            System.out.println("--- POBIERAM DANE Z SERWERA---");
            return weatherDataList;

        } catch (Exception e) {
            return weatherDataList;
        }
    }
}
