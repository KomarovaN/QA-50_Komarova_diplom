package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.PayPage;
import ru.netology.page.TravelPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelTest {
    private TravelPage travelPage;
    @BeforeEach
    public void setup() {
        travelPage = open("http://localhost:8080", TravelPage.class);
    }
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Should be visible the message from the bank about a successful pay if the card is 'approved'")
    public void shouldPayCardValidApproved() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo cardValidApproved = DataHelper.getCardInfoValidApproved();
        payPage.validPay(cardValidApproved);
        payPage.checkCloserMessageOK();
        DataHelper.CardStatus cardStatus = DBHelper.getCardStatusPay();
        String actualStatus = cardStatus.getStatus();
        String expectedStatus = "APPROVED";
        assertEquals (expectedStatus, actualStatus);
    }

    @Test
    @DisplayName("Should be visible the message from the bank about a not pay if the card is 'declined'")
    public void shouldPayCardValidDeclined() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo cardValidDeclined = DataHelper.getCardInfoValidDeclined();
        payPage.validPay(cardValidDeclined);
        payPage.checkCloserMessageError();
        DataHelper.CardStatus cardStatus = DBHelper.getCardStatusPay();
        String actualStatus = cardStatus.getStatus();
        String expectedStatus = "DECLINED";
        assertEquals (expectedStatus, actualStatus);
    }

    @Test
    @DisplayName("Should be visible the message from the bank about a not pay if the card is not exist")
    public void shouldPayCardNotExist() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo cardNotExist = DataHelper.getCardInfoNotExist();
        payPage.validPay(cardNotExist);
        payPage.checkCloserMessageError();
    }

    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the length of card number is less than 19 ")
    public void shouldFailedInputInvalidNumber() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidNumber();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the month is more than 12")
    public void shouldFailedInputInvalidMonthMore12() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidExpiredMonth();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the month is one digit")
    public void shouldFailedInputInvalidMonthOneDigit() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidMonth();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Истёк срок действия карты' if the year is less than the current one")
    public void shouldFailedInputInvalidYear() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidYear();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the holder name is not 'en' local")
    public void shouldFailedInputInvalidHolderLocal() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidHolderLocal();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the holder name is numbers")
    public void shouldFailedInputInvalidHolderNumber() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidHolderNumber();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the length of CVC is 2")
    public void shouldFailedInputInvalidCVC() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidCVC();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Поле обязательно для заполнения' if the card number field is empty")
    public void shouldFailedInputEmptyNumber() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoNoNumber();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should visible message 'Поле обязательно для заполнения' if the month field is empty")
    public void shouldFailedInputEmptyMonth() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoNoMonth();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should be visible message 'Поле обязательно для заполнения' if the year field is empty")
    public void shouldFailedInputEmptyYear() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoNoYear();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should be visible message 'Поле обязательно для заполнения' if the holder field is empty")
    public void shouldFailedInputEmptyHolder() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoNoHolder();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should be visible message 'Поле обязательно для заполнения' if the CVC field is empty")
    public void shouldFailedInputEmptyCVC() {
        PayPage payPage = travelPage.payTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoNoCVC();
        payPage.validPay(invalidCard);
        payPage.checkErrorMessage("Поле обязательно для заполнения");
    }
}
