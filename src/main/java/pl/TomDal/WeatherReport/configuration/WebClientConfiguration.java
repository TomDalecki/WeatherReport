package pl.TomDal.WeatherReport.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {

    public static final String WEATHER_FORECAST = "https://ai-weather-by-meteosource.p.rapidapi.com/";
    public static final int TIMEOUT = 5000;

    @Bean
    public WebClient webClient(final ObjectMapper objectMapper){
        final var httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .responseTimeout(Duration.ofMillis(TIMEOUT))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS)));

        final var exchangeStrategies = ExchangeStrategies
                .builder()
                .codecs(configure -> {
                    configure
                            .defaultCodecs()
                            .jackson2JsonEncoder(
                                    new Jackson2JsonEncoder(
                                            objectMapper,
                                            MediaType.APPLICATION_JSON
                                    )
                            );
                    configure
                            .defaultCodecs()
                            .jackson2JsonDecoder(
                                    new Jackson2JsonDecoder(
                                            objectMapper,
                                            MediaType.APPLICATION_JSON
                                    )
                            );
                })
                .build();

        return WebClient.builder()
                .baseUrl(WEATHER_FORECAST)
                .defaultHeader("X-RapidAPI-Key", "a10c5c1492mshe23237e54265b8cp138eadjsn7d30e99b16f2")
                .defaultHeader("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
                .exchangeStrategies(exchangeStrategies)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
