import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherData {

    private final String API_KEY = "5243954ce72e96f6e5110cc049e2cfd5";
    String START_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    String END_URL = "&appid=" + API_KEY + "&units=metric";

    public String getWeatherData(String city) throws IOException, InterruptedException {
        String fullUrl = START_URL + city + END_URL;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//        System.out.println("Antwort vom Server:");
//        System.out.println(response.body());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());

        double temperature = root.get("main").get("temp").asDouble();
        String description = root.get("weather").get(0).get("description").asText();

        System.out.println("Temperatur in " + city + ": " + temperature + " °C");
        System.out.println("Wetter: " + description);


        return response.body();
    }

}
