import java.text.DecimalFormat;
import java.util.Scanner;

public class InputOutput {

    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.println();
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void printError(String errorString) {
        System.err.println("[ERROR] " + errorString);
    }

    public void printWeather(String city, double currentTemperature, String description, String clouds, int cloudPercent, double windSpeed) {
        DecimalFormat df = new DecimalFormat("#.0");
        System.out.println("City: " + city);
        System.out.println("Current temperature: " + df.format(currentTemperature) + " °C");
        System.out.println("Description: " + description);
        if (clouds.equals("Clouds")) {
            System.out.println(clouds + ": " + cloudPercent + "%");
        } else {
            System.out.println(clouds);
        }
        System.out.println("Wind speed: " + df.format((windSpeed/1000)*60*60) + " km/h");
    }
}
