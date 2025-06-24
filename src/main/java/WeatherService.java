import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class WeatherService {

    private final HttpClient client = HttpClient.newHttpClient();

    public Optional<WeatherData> fetchWeather(String city) throws IOException, InterruptedException {
        String apiKey = "5243954ce72e96f6e5110cc049e2cfd5";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == WeatherFailureCodes.NOT_FOUND.get()) return Optional.empty();

        return parseWeather(response.body());
    }

    public Optional<WeatherData> parseWeather(String responseBody) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseBody);

        int temp = jsonNode.get("main").get("temp").asInt();
        String desc = jsonNode.get("weather").get(0).get("description").asText();
        return Optional.of(new WeatherData(temp, desc));
    }
}
