package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PayPage {
    private SelenideElement headerPay = $(byText("Оплата по карте"));
    private SelenideElement headerCredit = $(byText("Кредит по данным карты"));
    private SelenideElement numberField = $(".input__control", 0);
    private SelenideElement monthField = $(".input__control", 1);
    private SelenideElement yearField = $(".input__control", 2);
    private SelenideElement holderField = $(".input__control", 3);
    private SelenideElement cvcField = $(".input__control", 4);
    private SelenideElement continueButton = $(byText("Продолжить"));
    private static SelenideElement errorMessage = $(".input__sub");
    private static SelenideElement closerMessageOK = $(".notification__content",0);
    private static SelenideElement closerMessageError = $(".notification__content",1);
    //.notification_status_error  .notification__title "Ошибка" .notification__content "Ошибка! Банк отказал в проведении операции."
    //.notification_status_ok  .notification__title "Успешно" .notification__content "Операция одобрена Банком."

    public PayPage(String pay) {
        if (pay.equals("pay")) {
            headerPay.shouldBe(visible);
        }
        if (pay.equals("credit")) {
            headerCredit.shouldBe(visible);
        }
    }

    public void validPay(DataHelper.CardInfo cardInfo) {
        numberField.setValue(cardInfo.getNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        holderField.setValue(cardInfo.getHolder());
        cvcField.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    public void checkErrorMessage(String textMessage) {
        errorMessage.shouldHave(exactText(textMessage)).shouldBe(visible);
    }

    public void checkCloserMessageOK() {
        closerMessageOK.shouldHave(exactText("Операция одобрена Банком."))
                .shouldBe(visible,Duration.ofSeconds(15));
    }

    public void checkNotCloserMessageOK() {
        closerMessageError.shouldHave(exactText("Операция одобрена Банком."))
                .shouldBe(hidden,Duration.ofSeconds(15));
    }

    public void checkCloserMessageError() {
        closerMessageError.shouldHave(exactText("Ошибка! Банк отказал в проведении операции."))
                .shouldBe(visible,Duration.ofSeconds(15));
    }

    public void checkNotCloserMessageError() {
        closerMessageError.shouldHave(exactText("Ошибка! Банк отказал в проведении операции."))
                .shouldBe(hidden,Duration.ofSeconds(15));
    }
}
