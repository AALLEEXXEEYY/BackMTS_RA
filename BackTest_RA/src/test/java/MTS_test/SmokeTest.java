package MTS_test;

import api.models.CreateOrderRequest;
import api.input.CreateOrderResponseData;
import api.models.DeleteOrderRequest;
import api.input.ErrorError;
import api.input.GetStatusOrderData;
import api.steps.ReqresSteps;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import api.input.TariffsData;
import java.util.Random;
import io.qameta.allure.*;

@Epic("Api tests")
@Feature("Local project")
@Story("Методы для работы с пользователем")
@Link(name = "Локальный проект")
@Owner("Шестаков Алексей")
public class SmokeTest {
    private final ReqresSteps creditSteps = new ReqresSteps();
    private final Random random = new Random();
    private final static int last = 3;
    private final static int first = 1;
    private final int tariffIdTRYE = random.nextInt(last - first) + first;
    private final static int tariffIdFALCE = 10;
    private final static String orderIdempty = " ";
    private final static Long last_2 = 9999999999L;
    private final static Long first_2 = 1L;
    private final Long userID = random.nextLong(last_2 - first_2) + first_2;
    private final CreateOrderRequest createRequestT = new CreateOrderRequest(userID, tariffIdTRYE);
    private final CreateOrderRequest createRequestF = new CreateOrderRequest(userID, tariffIdFALCE);

    @Description("Отправка GET запроса и проверка статуса кода")
    @Test
    public void successGetTariffs() {
        TariffsData response = creditSteps.getSuccessTariffs();
    }

    @Description("Отправка позитивного POST запроса и проверка статуса кода")
    @Test
    public void successPostLoanApplication() {
        CreateOrderResponseData response = creditSteps.postSuccessApplication(createRequestT);
    }

    @Description("Отправка негативного POST запроса и проверка статуса кода")
    @Test
    public void unSuccessPostLoanApplication() {
        ErrorError response = creditSteps.postUnSuccessApplication(createRequestF);
    }

    @Description("Отправка позитивного DELETE запроса и проверка статуса кода")
    @Test
    public void successDeleteApplication() {
        String orderId = creditSteps.postSuccessApplication(createRequestT).getData().getOrderId();
        DeleteOrderRequest deleteOrderRequestTrue = new DeleteOrderRequest(userID, orderId);
        Response responseTrueDeleteApplication = creditSteps.deleteSuccessApplication(deleteOrderRequestTrue);
    }

    @Description("Отправка негативного DELETE запроса и проверка статуса кода")
    @Test
    public void unSuccessDeleteSuccessApplication() {
        DeleteOrderRequest deleteOrderRequestFalse = new DeleteOrderRequest(userID, orderIdempty);
        ErrorError responseFalseDeleteApplication = creditSteps.deleteUnSuccessApplication(deleteOrderRequestFalse);
    }

    @Description("POST+GET+статус код")
    @Test
    public void successGetStatusApplication() {
        String orderId = creditSteps.postSuccessApplication(createRequestT).getData().getOrderId();
        GetStatusOrderData responseStatusApplication = creditSteps.getSuccessStatusApplication(orderId);
    }

}