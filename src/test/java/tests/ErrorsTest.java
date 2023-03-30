package tests;

import info.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class ErrorsTest {
    MainPage main;

    @BeforeEach
    void openChrome() {main = open("http://localhost:8080/", MainPage.class);}

    @Test
    void checkErrorEmptyNumberField() {
        var mainError = main.linkToMainPageErrors();
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

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkErrorEmptyMonthField() {
        var mainError = main.linkToMainPageErrors();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                null,
                data.getYear(),
                data.getHolder(),
                data.getPin());

        main.clickContinue();

        String expected = "Неверный формат";
        String actual = mainError.getIncorrectFormat();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkErrorWrongMonthNumberField() {
        var mainError = main.linkToMainPageErrors();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                "22",
                data.getYear(),
                data.getHolder(),
                data.getPin());

        main.clickContinue();

        String expected = "Неверно указан срок действия карты";
        String actual = mainError.getWrongMonth();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkErrorEmptyYearField() {
        var mainError = main.linkToMainPageErrors();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                data.getMonth(),
                null,
                data.getHolder(),
                data.getPin());

        main.clickContinue();

        String expected = "Неверный формат";
        String actual = mainError.getIncorrectFormat();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkErrorWrongYearField() {
        var mainError = main.linkToMainPageErrors();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                data.getMonth(),
                "10",
                data.getHolder(),
                data.getPin());

        main.clickContinue();

        String expected = "Истёк срок действия карты";
        String actual = mainError.getWrongYear();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkErrorEmptyHolderField() {
        var mainError = main.linkToMainPageErrors();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                data.getMonth(),
                data.getYear(),
                null,
                data.getPin());

        main.clickContinue();

        String expected = "Поле обязательно для заполнения";
        String actual = mainError.getEmptyHolder();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkErrorEmptyPinField() {
        var mainError = main.linkToMainPageErrors();
        var data = DataHelper.card();

        main.visibleHeadline();
        main.buyingProcess();

        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                data.getMonth(),
                data.getYear(),
                data.getHolder(),
                null);

        main.clickContinue();

        String expected = "Неверный формат";
        String actual = mainError.getIncorrectFormat();

        Assertions.assertEquals(expected, actual);
    }

}
