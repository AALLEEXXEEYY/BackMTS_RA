package api.steps;

import api.models.CreateOrderRequest;
import api.input.CreateOrderResponseData;
import api.models.DeleteOrderRequest;
import api.input.ErrorError;
import api.input.GetStatusOrderData;
import api.input.TariffsData;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;

public class ReqresSteps {
    @Step("GET запрос")
    public static TariffsData getSuccessTariffs() {

        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .get("getTariffs")
                .then()
                .spec(SpecHelper.getResponseSpec(200))
                .extract()
                .body()
                .jsonPath()
                .getObject(".", TariffsData.class);
    }
    @Step("Позитивный GET запрос статуса заявки")
    public static GetStatusOrderData getSuccessStatusApplication(String orderId) {

        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .get(String.format("getStatusOrder?orderId=%s", orderId))
                .then()
                .spec(SpecHelper.getResponseSpec(200))
                .extract()
                .body()
                .jsonPath()
                .getObject(".", GetStatusOrderData.class);
    }
    @Step("Позитивный POST запрос")
    public static CreateOrderResponseData postSuccessApplication(CreateOrderRequest createOrderRequest) {

        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .body(createOrderRequest)
                .post("order")
                .then()
                .spec(SpecHelper.getResponseSpec(200))
                .extract()
                .body()
                .jsonPath()
                .getObject(".", CreateOrderResponseData.class);
    }
    @Step("Негативный POST запрос")
    public static ErrorError postUnSuccessApplication(CreateOrderRequest createOrderRequest) {

        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .body(createOrderRequest)
                .post("order")
                .then()
                .spec(SpecHelper.getResponseSpec(400))
                .extract()
                .body()
                .jsonPath()
                .getObject(".", ErrorError.class);
    }
    @Step("Пустой POST запрос")
    public static ErrorError postEmptyApplication() {

        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .post("order")
                .then()
                .spec(SpecHelper.getResponseSpec(500))
                .extract()
                .body()
                .jsonPath()
                .getObject(".", ErrorError.class);
    }
    @Step("Позитивный DELETE запрос")
    public static Response deleteSuccessApplication(DeleteOrderRequest deleteOrderRequest) {

        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .body(deleteOrderRequest)
                .delete("deleteOrder")
                .then()
                .spec(SpecHelper.getResponseSpec(200))
                .extract()
                .response();
    }
    @Step("Негативный DELETE запрос ")
    public static ErrorError deleteUnSuccessApplication(DeleteOrderRequest deleteOrderRequest) {

        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .body(deleteOrderRequest)
                .delete("deleteOrder")
                .then()
                .spec(SpecHelper.getResponseSpec(400))
                .extract()
                .body()
                .jsonPath()
                .getObject(".", ErrorError.class);
    }
    @Step("Пустой DELETE запрос")
    public static ErrorError deleteUnSuccessApplication() {

        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .delete("deleteOrder")
                .then()
                .spec(SpecHelper.getResponseSpec(500))
                .extract()
                .body()
                .jsonPath()
                .getObject(".", ErrorError.class);
    }
    @Step("Проверка полученного ответа и ожидаемого ответа")
    public void checkFields(String responseField, String expectField) {
        Assertions.assertEquals(responseField, expectField);
    }

}
