public enum WeatherFailureCodes {

    NOT_FOUND(404);

    private final int failureCode;

    WeatherFailureCodes(int failureCode) {this.failureCode = failureCode;}

    public int get() {
        return failureCode;
    }

}

