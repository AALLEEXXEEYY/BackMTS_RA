package api.constant;

public enum ErrorCode {
    TARIFF_NOT_FOUND("Тариф не найден"),
    ORDER_NOT_FOUND("Заявка не найдена"),
    LOAN_CONSIDERATION("Заявка уже на рассмотрении"),
    INTERNAL_SERVER_ERROR("Внутренняя ошибка сервера");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getError() {
        return message;
    }
}
