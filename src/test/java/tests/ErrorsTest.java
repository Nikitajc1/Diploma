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

        main.fieldInfo(null,
                data.getMonth(),
                data.getYear(),
                data.getHolder(),
                data.getPin());

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

        main.fieldInfo(DataHelper.approved().getNumber(),
                null,
                data.getYear(),
                data.getHolder(),
                data.getPin());

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

        main.fieldInfo(DataHelper.approved().getNumber(),
                "22",
                data.getYear(),
                data.getHolder(),
                data.getPin());

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

        main.fieldInfo(DataHelper.approved().getNumber(),
                data.getMonth(),
                null,
                data.getHolder(),
                data.getPin());

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

        main.fieldInfo(DataHelper.approved().getNumber(),
                data.getMonth(),
                "10",
                data.getHolder(),
                data.getPin());

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

        main.fieldInfo(DataHelper.approved().getNumber(),
                data.getMonth(),
                data.getYear(),
                null,
                data.getPin());

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

        main.fieldInfo(DataHelper.approved().getNumber(),
                data.getMonth(),
                data.getYear(),
                data.getHolder(),
                null);

        main.clickContinue();

        String expected = "Неверный формат";
        String actual = mainError.getIncorrectFormat();

        Assertions.assertTrue(expected.equals(actual));
    }

}
