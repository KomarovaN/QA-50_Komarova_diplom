package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TravelPage {
    private SelenideElement payButton = $(byText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));

    public PayPage payTravel () {
        payButton.click();
        return new PayPage("pay");
    }

    public PayPage creditTravel () {
        creditButton.click();
        return new PayPage("credit");
    }

}
