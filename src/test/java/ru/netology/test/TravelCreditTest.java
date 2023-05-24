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

public class TravelCreditTest {

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
    @DisplayName("Should be visible the message from the bank about a successful credit pay if the card is 'approved'")
    public void shouldCreditCardValidApproved() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo cardValidApproved = DataHelper.getCardInfoValidApproved();
        creditPage.validPay(cardValidApproved);
        creditPage.checkCloserMessageOK();
        DataHelper.CardStatus cardStatus = DBHelper.getCardStatusCredit();
        String actualStatus = cardStatus.getStatus();
        String expectedStatus = "APPROVED";
        assertEquals (expectedStatus, actualStatus);
    }
    @Test
    @DisplayName("Should NOT be visible the message from the bank about a NOT credit pay if the card is 'approved'")
    public void shouldNotCreditCardValidApproved() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo cardValidApproved = DataHelper.getCardInfoValidApproved();
        creditPage.validPay(cardValidApproved);
        creditPage.checkNotCloserMessageError();
    }

    @Test
    @DisplayName("Should be visible the message from the bank about a not credit pay if the card is 'declined'")
    public void shouldCreditCardValidDeclined() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo cardValidDeclined = DataHelper.getCardInfoValidDeclined();
        creditPage.validPay(cardValidDeclined);
        creditPage.checkCloserMessageError();
        DataHelper.CardStatus cardStatus = DBHelper.getCardStatusCredit();
        String actualStatus = cardStatus.getStatus();
        String expectedStatus = "DECLINED";
        assertEquals (expectedStatus, actualStatus);
    }
    @Test
    @DisplayName("Should NOT be visible the message from the bank about a successful credit pay if the card is 'declined'")
    public void shouldNotCreditCardValidDeclined() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo cardValidDeclined = DataHelper.getCardInfoValidDeclined();
        creditPage.validPay(cardValidDeclined);
        creditPage.checkNotCloserMessageOK();
    }
    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the length of card number is less than 19 ")
    public void shouldFailedInputInvalidNumber() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidNumber();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the month is more than 12")
    public void shouldFailedInputInvalidMonthMore12() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidExpiredMonth();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the month is one digit")
    public void shouldFailedInputInvalidMonthOneDigit() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidMonth();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Истёк срок действия карты' if the year is less than the current one")
    public void shouldFailedInputInvalidYear() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidYear();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the holder name is not 'en' local")
    public void shouldFailedInputInvalidHolderLocal() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidHolderLocal();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the holder name is numbers")
    public void shouldFailedInputInvalidHolderNumber() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidHolderNumber();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Неверный формат' if the length of CVC is 2")
    public void shouldFailedInputInvalidCVC() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoInvalidCVC();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Неверный формат");
    }

    @Test
    @DisplayName("Should be visible message 'Поле обязательно для заполнения' if the card number field is empty")
    public void shouldFailedInputEmptyNumber() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoNoNumber();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should visible message 'Поле обязательно для заполнения' if the month field is empty")
    public void shouldFailedInputEmptyMonth() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoNoMonth();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should be visible message 'Поле обязательно для заполнения' if the year field is empty")
    public void shouldFailedInputEmptyYear() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoNoYear();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should be visible message 'Поле обязательно для заполнения' if the holder field is empty")
    public void shouldFailedInputEmptyHolder() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoNoHolder();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should be visible message 'Поле обязательно для заполнения' if the CVC field is empty")
    public void shouldFailedInputEmptyCVC() {
        PayPage creditPage = travelPage.creditTravel();
        DataHelper.CardInfo invalidCard = DataHelper.getCardInfoNoCVC();
        creditPage.validPay(invalidCard);
        creditPage.checkErrorMessage("Поле обязательно для заполнения");
    }
}
