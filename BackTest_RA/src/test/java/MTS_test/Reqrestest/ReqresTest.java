package MTS_test.Reqrestest;

import api.constant.ErrorCode;
import api.constant.OrderStatus;
import api.constant.TariffType;
import api.input.TariffsData;
import api.input.ErrorError;
import api.input.GetStatusOrderData;
import api.models.CreateOrderRequest;
import api.input.CreateOrderResponseData;
import api.models.DeleteOrderRequest;
import api.steps.ReqresSteps;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.Random;
import io.qameta.allure.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Epic("Api tests")
@Feature("Local project")
@Story("Методы для работы с пользователем")
@Link(name = "Локальный проект")
@Owner("Шестаков Алексей")
public class ReqresTest {
    private final ReqresSteps creditSteps = new ReqresSteps();
    private final Random random = new Random();
    private final static int last = 3;
    private final static int first = 1;
    private final int tariffIdTRYE = random.nextInt(last - first) + first;
    private final static Long last_2 = 9999999999L;
    private final static Long first_2 = 1L;
    private final Long userID = random.nextLong(last_2 - first_2) + first_2;
    private final CreateOrderRequest createOrderRequestOrderT = new CreateOrderRequest(userID, tariffIdTRYE);
    private final CreateOrderRequest createOrderRequestOrderF = new CreateOrderRequest(userID, tariffIdFalse);
    private final static int tariffIdFalse = 10;
    private final static String orderIdFalse = " ";

    @Description("Оправка GET запроса,проверка статуса кода и id,type,interestRate")
    @Test
    public void CheckTariffs() {
        TariffsData responseGetTariffs = creditSteps.getSuccessTariffs();

        creditSteps.checkFields(responseGetTariffs.getData().getTariffs().get(0).getId().toString(), "1");
        creditSteps.checkFields(responseGetTariffs.getData().getTariffs().get(1).getId().toString(), "2");
        creditSteps.checkFields(responseGetTariffs.getData().getTariffs().get(2).getId().toString(), "3");

        creditSteps.checkFields(responseGetTariffs.getData().getTariffs().get(0).getType(), TariffType.CONSUMER.toString());
        creditSteps.checkFields(responseGetTariffs.getData().getTariffs().get(1).getType(), TariffType.MORTGAGE.toString());
        creditSteps.checkFields(responseGetTariffs.getData().getTariffs().get(2).getType(), TariffType.INTRABANK.toString());

        creditSteps.checkFields(responseGetTariffs.getData().getTariffs().get(0).getInterestRate(), TariffType.CONSUMER.getValue().toString());
        creditSteps.checkFields(responseGetTariffs.getData().getTariffs().get(1).getInterestRate(), TariffType.MORTGAGE.getValue().toString());
        creditSteps.checkFields(responseGetTariffs.getData().getTariffs().get(2).getInterestRate(), TariffType.INTRABANK.getValue().toString());
    }
    @Description("Оправляем позитивный POST запрос и GET запрос, проверяека статуса кода и ответа ")
    @Test
    public void checkInProgress() {
        String orderId = creditSteps.postSuccessApplication(createOrderRequestOrderT).getData().getOrderId();
        GetStatusOrderData responseStatusApplication = creditSteps.getSuccessStatusApplication(orderId);

        creditSteps.checkFields(responseStatusApplication.getData().getOrderStatus(), OrderStatus.IN_PROGRESS.toString());
    }
    @Description("Отправляем позитивный,параметризованный POST на все тарифы,проверка статуса кода")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void successPostParametrizedLoanApplication(int tariffId) {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(userID, tariffId);
        CreateOrderResponseData response = creditSteps.postSuccessApplication(createOrderRequest);
    }
    @Description("Оправляем пустое тело POST запросом, проверка статуса кода")
    @Test
    public void checkPostEmptyInternalError() {
        ErrorError response = creditSteps.postEmptyApplication();

        creditSteps.checkFields(response.getError().getCode(), ErrorCode.INTERNAL_SERVER_ERROR.toString());
        creditSteps.checkFields(response.getError().getMessage(), ErrorCode.INTERNAL_SERVER_ERROR.getError());
    }
    @Description("Оправляем негативный POST запрос с несуществующим тарифом,проверка статуса кода")
    @Test
    public void checkPostNotFoundError() {
        ErrorError responseFalseLoanApplication = creditSteps.postUnSuccessApplication(createOrderRequestOrderF);

        creditSteps.checkFields(responseFalseLoanApplication.getError().getCode(), ErrorCode.TARIFF_NOT_FOUND.toString());
        creditSteps.checkFields(responseFalseLoanApplication.getError().getMessage(), ErrorCode.TARIFF_NOT_FOUND.getError());
    }
    @Description("Отправляем POST запрос 2 раза с одинаковыми входными данными,проверка статуса кода")
    @Test
    public void postLoanConsiderationError() {
        CreateOrderResponseData responseTrue = creditSteps.postSuccessApplication(createOrderRequestOrderT);
        ErrorError responseFalse = creditSteps.postUnSuccessApplication(createOrderRequestOrderT);

        creditSteps.checkFields(responseFalse.getError().getCode(), ErrorCode.LOAN_CONSIDERATION.toString());
        creditSteps.checkFields(responseFalse.getError().getMessage(), ErrorCode.LOAN_CONSIDERATION.getError());
    }
    @Description("Оправляем DELETE запрос,проверка статуса кода")
    @Test
    public void successDeleteApplication() {
        String orderId = creditSteps.postSuccessApplication(createOrderRequestOrderT).getData().getOrderId();
        DeleteOrderRequest deleteOrderRequestTrue = new DeleteOrderRequest(userID, orderId);
        Response responseTrueDeleteApplication = creditSteps.deleteSuccessApplication(deleteOrderRequestTrue);
    }
    @Description("Оправляем негативный DELETE с пустым телом запрос,проверка статуса кода")
    @Test
    public void checkDeleteEmptyInternalError() {
        ErrorError response = creditSteps.deleteUnSuccessApplication();

        creditSteps.checkFields(response.getError().getCode(), ErrorCode.INTERNAL_SERVER_ERROR.toString());
        creditSteps.checkFields(response.getError().getMessage(), ErrorCode.INTERNAL_SERVER_ERROR.getError());
    }
    @Description("Оправляем негативный DELETE с несуществующим тарифом запрос,проверка статуса кода")
    @Test
    public void checkDeleteOrderNotFoundError() {
        DeleteOrderRequest deleteOrderRequestFalse = new DeleteOrderRequest(userID, orderIdFalse);
        ErrorError responseFalse = creditSteps.deleteUnSuccessApplication(deleteOrderRequestFalse);

        creditSteps.checkFields(responseFalse.getError().getCode(), ErrorCode.ORDER_NOT_FOUND.toString());
        creditSteps.checkFields(responseFalse.getError().getMessage(), ErrorCode.ORDER_NOT_FOUND.getError());
    }
    @Description("Оправляем POST запрос с отрицательным userId,проверка статуса кода")
    @Test
    public void UnSuccessEmptyUserIdPostLoanApplication() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(-userID, tariffIdTRYE);

        ErrorError response = creditSteps.postUnSuccessApplication(createOrderRequest);
    }
}
