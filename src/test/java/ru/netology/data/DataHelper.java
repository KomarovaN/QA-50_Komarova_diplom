package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    public static CardInfo getCardInfoValidApproved() {
        return new CardInfo(
                getNumberApproved(),
                generateExpireYear(),
                generateExpireMonth(),
                generateHolder("en"),
                generateCVC());
    }

    public static CardInfo getCardInfoValidDeclined() {
        return new CardInfo(
                getNumberDeclined(),
                generateExpireYear(),
                generateExpireMonth(),
                generateHolder("en"),
                generateCVC());
    }

    public static CardInfo getCardInfoNotExist() {
        return new CardInfo(
                getNumberNotExist(),
                generateExpireYear(),
                generateExpireMonth(),
                generateHolder("en"),
                generateCVC());
    }
    public static CardInfo getCardInfoInvalidNumber() {
        return new CardInfo(
                generateInvalidNumber(),
                generateExpireYear(),
                generateExpireMonth(),
                generateHolder("en"),
                generateCVC());
    }

    public static CardInfo getCardInfoInvalidYear() {
        return new CardInfo(
                getNumberApproved(),
                generateInvalidExpireYear(),
                generateExpireMonth(),
                generateHolder("en"),
                generateCVC());
    }

    public static CardInfo getCardInfoInvalidExpiredMonth() {
        return new CardInfo(
                getNumberApproved(),
                generateExpireYear(),
                generateInvalidExpiredMonth(),
                generateHolder("en"),
                generateCVC());
    }

    public static CardInfo getCardInfoInvalidMonth() {
        return new CardInfo(
                getNumberApproved(),
                generateExpireYear(),
                generateInvalidMonth(),
                generateHolder("en"),
                generateCVC());
    }

    public static CardInfo getCardInfoInvalidCVC() {
        return new CardInfo(
                getNumberApproved(),
                generateExpireYear(),
                generateExpireMonth(),
                generateHolder("en"),
                generateInvalidCVC());
    }

    public static CardInfo getCardInfoInvalidHolderLocal() {
        return new CardInfo(
                getNumberApproved(),
                generateExpireYear(),
                generateExpireMonth(),
                generateHolder("uk"),
                generateCVC());
    }

    public static CardInfo getCardInfoInvalidHolderNumber() {
        return new CardInfo(
                getNumberApproved(),
                generateExpireYear(),
                generateExpireMonth(),
                generateInvalidHolder(),
                generateCVC());
    }

    public static CardInfo getCardInfoNoNumber() {
        return new CardInfo(
                "",
                generateExpireYear(),
                generateExpireMonth(),
                generateHolder("en"),
                generateCVC());
    }

    public static CardInfo getCardInfoNoMonth() {
        return new CardInfo(
                getNumberApproved(),
                generateExpireYear(),
                "",
                generateHolder("en"),
                generateCVC());
    }

    public static CardInfo getCardInfoNoYear() {
        return new CardInfo(
                getNumberApproved(),
                "",
                generateExpireMonth(),
                generateHolder("en"),
                generateCVC());
    }

    public static CardInfo getCardInfoNoCVC() {
        return new CardInfo(
                getNumberApproved(),
                generateExpireYear(),
                generateExpireMonth(),
                generateHolder("en"),
                "");
    }

    public static CardInfo getCardInfoNoHolder() {
        return new CardInfo(getNumberApproved(), generateExpireYear(), generateExpireMonth(), "", generateCVC());
    }

    public static String getNumberApproved() {
        return "4444 4444 4444 4441";
    }
    public static String getNumberDeclined() {
        return "4444 4444 4444 4442";
    }
    public static String getNumberNotExist() {
        return "4444 4444 4444 4444";
    }

    public static String generateInvalidNumber() {
        Faker faker = new Faker();
        return faker.number().digits(8);
    }

    public static String generateHolder(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generateInvalidHolder() {
        return "fg385fk%^#rtiwegrу_3---3у1ш22%№9";
    }

    public static String generateExpireMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateInvalidExpiredMonth() {
        Faker faker = new Faker();
        Integer invalidMonth = faker.number().numberBetween(13,99);
        return invalidMonth.toString();
    }

    public static String generateInvalidMonth() {
        Faker faker = new Faker();
        Integer invalidMonth = faker.number().numberBetween(0,9);
        return invalidMonth.toString();
    }

    public static String generateExpireYear() {
        return LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateInvalidExpireYear() {
        return LocalDate.now().minusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateCVC() {
        Faker faker = new Faker();
        return faker.number().digits(3);
    }

    public static String generateInvalidCVC() {
        Faker faker = new Faker();
        return faker.number().digits(2);
    }

    @Value
    public static class CardInfo {
        String number;
        String year;
        String month;
        String holder;
        String cvc;
    }
    // в браузере при отправке запроса в body - {number: "", year: "", month: "", holder: "", cvc: ""}

    @Value
    public static class CardStatus {
        String status;
    }
}