import java.util.Scanner;

public class InputOutput {

    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void printError(String errorString) {
        System.err.println("[ERROR] " + errorString);
        scanner.nextLine();
    }

    public void printWeather(String city, int temperature, String description) {
        System.out.println("Temperature in " + city + ": " + temperature + " °C");
        System.out.println("Description: " + description);
    }
}
