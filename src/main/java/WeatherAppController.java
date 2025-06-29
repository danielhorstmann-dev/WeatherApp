import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.Optional;

public class WeatherAppController {

    private final InputOutput io = new InputOutput();
    private final WeatherService weatherService = new WeatherService();

    public void run(){
        while (true) {
            String city = getCityFromUser();
            if (city.isEmpty()) continue;
            if (city.equalsIgnoreCase("exit")) break;

            String cityFormatted = city.replace(" ", "%20");

            try {
                Optional<WeatherData> dataOpt = weatherService.fetchWeather(cityFormatted);
                dataOpt.ifPresent(data -> io.printWeather(city, data.currentTemperature(), data.description(), data.clouds(), data.cloudPercent(), data.windSpeed()));
            } catch (JsonProcessingException e) {
                InputOutput.printError("Error to read Json");
                InputOutput.printError(e.getMessage());
            } catch (IOException | InterruptedException e) {
                InputOutput.printError("Server does not respond");
                InputOutput.printError(e.getMessage());
            }
        }
    }

    private String getCityFromUser() {
        return io.readString("City: ").trim();
    }

}