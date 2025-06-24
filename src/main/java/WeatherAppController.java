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

            try {
                Optional<WeatherData> dataOpt = weatherService.fetchWeather(city);
                dataOpt.ifPresent(data -> io.printWeather(city, data.temperature(), data.description()));
            } catch (JsonProcessingException e) {
                io.printError("Error to read Json");
                io.printError(e.getMessage());
            } catch (IOException | InterruptedException e) {
                io.printError("Server does not respond");
                io.printError(e.getMessage());
            }
        }
    }

    private String getCityFromUser() {
        return io.readString("City: ").trim();
    }

}