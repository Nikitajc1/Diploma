package tests;

import info.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.MainPage;
import pageObject.MainPageErrors;

import static com.codeborne.selenide.Selenide.open;

public class ErrorsTest {
    MainPage main;

    @BeforeEach
    void openChrome() {main = open("http://localhost:8080/", MainPage.class);}

    @Test
    void checkErrorEmptyNumberField() {
        var mainError = main.link();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.numberField(null);
        main.monthField(data.getMonth());
        main.yearField(data.getYear());
        main.holderField(data.getHolder());
        main.pinField(data.getPin());

        main.clickContinue();

        String expected = "Неверный формат";
        String actual = mainError.getIncorrectFormat();

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void checkErrorEmptyMonthField() {
        var mainError = main.link();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.numberField(DataHelper.approved().getNumber());
        main.monthField(null);
        main.yearField(data.getYear());
        main.holderField(data.getHolder());
        main.pinField(data.getPin());

        main.clickContinue();

        String expected = "Неверный формат";
        String actual = mainError.getIncorrectFormat();

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void checkErrorWrongMonthNumberField() {
        var mainError = main.link();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.numberField(DataHelper.approved().getNumber());
        main.monthField("22");
        main.yearField(data.getYear());
        main.holderField(data.getHolder());
        main.pinField(data.getPin());

        main.clickContinue();

        String expected = "Неверно указан срок действия карты";
        String actual = mainError.getWrongMonth();

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void checkErrorEmptyYearField() {
        var mainError = main.link();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.numberField(DataHelper.approved().getNumber());
        main.monthField(data.getMonth());
        main.yearField(null);
        main.holderField(data.getHolder());
        main.pinField(data.getPin());

        main.clickContinue();

        String expected = "Неверный формат";
        String actual = mainError.getIncorrectFormat();

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void checkErrorWrongYearField() {
        var mainError = main.link();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.numberField(DataHelper.approved().getNumber());
        main.monthField(data.getMonth());
        main.yearField("10");
        main.holderField(data.getHolder());
        main.pinField(data.getPin());

        main.clickContinue();

        String expected = "Истёк срок действия карты";
        String actual = mainError.getWrongYear();

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void checkErrorEmptyHolderField() {
        var mainError = main.link();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.numberField(DataHelper.approved().getNumber());
        main.monthField(data.getMonth());
        main.yearField(data.getYear());
        main.holderField(null);
        main.pinField(data.getPin());

        main.clickContinue();

        String expected = "Поле обязательно для заполнения";
        String actual = mainError.getEmptyHolder();

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void checkErrorEmptyPinField() {
        var mainError = main.link();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.numberField(DataHelper.approved().getNumber());
        main.monthField(data.getMonth());
        main.yearField(data.getYear());
        main.holderField(data.getHolder());
        main.pinField(null);

        main.clickContinue();

        String expected = "Неверный формат";
        String actual = mainError.getIncorrectFormat();

        Assertions.assertTrue(expected.equals(actual));
    }

}
