import java.util.List;

public enum WeatherFailureCodes {

    NOT_FOUND(404),
    API_FAILURE(401);

    private final int failureCode;

    WeatherFailureCodes(int failureCode) {this.failureCode = failureCode;}

    private int get() {
        return failureCode;
    }

    public static boolean isFailureCode(int statusCode) {
        List<WeatherFailureCodes> failureCodes = List.of(NOT_FOUND, API_FAILURE);
        return failureCodes.stream().anyMatch(code -> code.get() == statusCode);
    }

}

