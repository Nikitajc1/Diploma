package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import info.DataHelper;
import info.DatabaseProcess;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import pageObject.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class ServiceTest {
    MainPage main;
    @BeforeEach
    void openChrome() {
        main = open("http://localhost:8080/", MainPage.class);
    }

    @SneakyThrows
    @AfterEach
    void clear() {DatabaseProcess.clean();}
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }


    @Test
    void approvedBuy() {
        var data = DataHelper.card();

        main.visibleHeadline();
        main.visiblePicture();
        main.buyingProcess();
        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                data.getMonth(),
                data.getYear(),
                data.getHolder(),
                data.getPin());
        main.clickContinue();
        main.successCheck();

        DatabaseProcess.buyingStatus();

        String expected = "APPROVED";
        String actual = DatabaseProcess.buyingStatus();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void declinedBuy() {
        var data = DataHelper.card();

        main.visibleHeadline();
        main.visiblePicture();
        main.buyingProcess();
        main.fieldInfo(DataHelper.getDeclinedCard().getNumber(),
                data.getMonth(),
                data.getYear(),
                data.getHolder(),
                data.getPin());
        main.clickContinue();
        main.successCheck();

        DatabaseProcess.buyingStatus();

        String expected = "DECLINED";
        String actual = DatabaseProcess.buyingStatus();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void approvedCredit() {
        var data = DataHelper.card();

        main.visibleHeadline();
        main.visiblePicture();
        main.creditProcess();
        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                data.getMonth(),
                data.getYear(),
                data.getHolder(),
                data.getPin());
        main.clickContinue();
        main.successCheck();

        DatabaseProcess.creditStatus();

        String expected = "APPROVED";
        String actual = DatabaseProcess.creditStatus();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void declinedCredit() {
        var data = DataHelper.card();

        main.visibleHeadline();
        main.visiblePicture();
        main.creditProcess();
        main.fieldInfo(DataHelper.getDeclinedCard().getNumber(),
                data.getMonth(),
                data.getYear(),
                data.getHolder(),
                data.getPin());
        main.clickContinue();
        main.successCheck();

        DatabaseProcess.creditStatus();

        String expected = "DECLINED";
        String actual = DatabaseProcess.creditStatus();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void abortOperation() {
        var data = DataHelper.card();

        main.visibleHeadline();
        main.visiblePicture();
        main.buyingProcess();
        main.fieldInfo(DataHelper.getRandomCard().getNumber(),
                data.getMonth(),
                data.getYear(),
                data.getHolder(),
                data.getPin());
        main.clickContinue();
        main.failCheck();
        sleep(8000);
        main.getSuccessNotification().shouldNotBe(Condition.visible);
    }

    @Test
    void approvedBuyCheckCorrectAmount() {
        var data = DataHelper.card();

        main.visibleHeadline();
        main.visiblePicture();
        main.buyingProcess();
        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                data.getMonth(),
                data.getYear(),
                data.getHolder(),
                data.getPin());
        main.clickContinue();
        main.successCheck();

        DatabaseProcess.buyingAmount();

        int expected = 45000;
        int actual = DatabaseProcess.buyingAmount();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void validationCVC() {
        var data = DataHelper.card();

        main.visibleHeadline();
        main.visiblePicture();
        main.buyingProcess();
        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                data.getMonth(),
                data.getYear(),
                data.getHolder(),
                "000");
        main.clickContinue();
        sleep(8000);
        main.getSuccessNotification().shouldNotBe(Condition.visible);
    }

    @Test
    void validationHolder() {
        var data = DataHelper.card();

        main.visibleHeadline();
        main.visiblePicture();
        main.buyingProcess();
        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                data.getMonth(),
                data.getYear(),
                "??%ГТЖ",
                data.getPin());
        main.clickContinue();
        sleep(8000);
        main.getSuccessNotification().shouldNotBe(Condition.visible);
    }

    @Test
    void validationMonth() {
        var data = DataHelper.card();

        main.visibleHeadline();
        main.visiblePicture();
        main.buyingProcess();
        main.fieldInfo(DataHelper.getApprovedCard().getNumber(),
                "00",
                data.getYear(),
                data.getHolder(),
                data.getPin());
        main.clickContinue();
        sleep(8000);
        main.getSuccessNotification().shouldNotBe(Condition.visible);
    }
}
