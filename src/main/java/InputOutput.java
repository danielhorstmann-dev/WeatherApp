import java.util.Scanner;

public class InputOutput {

    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void printError(String errorString) {
        System.err.println(errorString);
    }
}
