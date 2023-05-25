package api.constant;

public enum TariffType {

    CONSUMER("11%"),
    MORTGAGE("7%"),
    INTRABANK("8%");

    private final String value;

    TariffType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
