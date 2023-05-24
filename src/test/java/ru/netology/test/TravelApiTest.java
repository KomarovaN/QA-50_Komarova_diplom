package ru.netology.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;

import static io.restassured.RestAssured.*;

public class TravelApiTest {
    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    @DisplayName("Should be successful payment with approved card")
    void shouldSuccessfulPayment() {
        // Given - When - Then
        // Предусловия
        given()
                .spec(requestSpec)
                .body(DataHelper.getCardInfoValidApproved())
                // Выполняемые действия
                .when()
                .post("/api/v1/pay")
                // Проверки
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Should be successful credit request with approved card")
    void shouldSuccessfulCreditRequest() {
        // Given - When - Then
        // Предусловия
        given()
                .spec(requestSpec)
                .body(DataHelper.getCardInfoValidApproved())
                // Выполняемые действия
                .when()
                .post("/api/v1/credit")
                // Проверки
                .then()
                .statusCode(200);
    }
}
