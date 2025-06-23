import java.io.IOException;

public class WeatherAppController {

    private final InputOutput io = new InputOutput();
    private final WeatherData weatherData = new WeatherData();

    public void run(){
        String city = io.readString("City: ").trim();

        String result = null;
        try {
            result = weatherData.getWeatherData(city);
        } catch (IOException e) {
            io.printError("IO-Error: " + e.getMessage());
        } catch (InterruptedException e) {
            io.printError("Interrupted-Error: " + e.getMessage());
        }
        System.out.println(result);
    }

}
