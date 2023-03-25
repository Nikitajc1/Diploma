package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import info.DataHelper;
import info.DatabaseProcess;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pageObject.MainPage;
import java.sql.SQLException;
import static com.codeborne.selenide.Selenide.open;

public class ServiceTest {
    MainPage main;
    @BeforeEach
    void openChrome() {
        main = open("http://localhost:8080/", MainPage.class);
    }

    @AfterEach
    void clear() {
        try {
            DatabaseProcess.clean();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
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
        main.visibleHeadline();
        main.visiblePicture();
        main.buyingProcess();
        main.fieldInfo(DataHelper.approved().getNumber());
        main.clickContinue();
        main.successCheck();

        DatabaseProcess.buyingStatus();

        String expected = "APPROVED";
        String actual = DatabaseProcess.buyingStatus();

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void declinedBuy() {
        main.visibleHeadline();
        main.visiblePicture();
        main.buyingProcess();
        main.fieldInfo(DataHelper.declined().getNumber());
        main.clickContinue();
        main.successCheck();

        DatabaseProcess.buyingStatus();

        String expected = "DECLINED";
        String actual = DatabaseProcess.buyingStatus();

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void approvedCredit() {
        main.visibleHeadline();
        main.visiblePicture();
        main.creditProcess();
        main.fieldInfo(DataHelper.approved().getNumber());
        main.clickContinue();
        main.successCheck();

        DatabaseProcess.creditStatus();

        String expected = "APPROVED";
        String actual = DatabaseProcess.creditStatus();

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void declinedCredit() {
        main.visibleHeadline();
        main.visiblePicture();
        main.creditProcess();
        main.fieldInfo(DataHelper.declined().getNumber());
        main.clickContinue();
        main.successCheck();

        DatabaseProcess.creditStatus();

        String expected = "DECLINED";
        String actual = DatabaseProcess.creditStatus();

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void abortOperation() {
        main.visibleHeadline();
        main.visiblePicture();
        main.buyingProcess();
        main.fieldInfo(DataHelper.random().getNumber());
        main.clickContinue();
        main.failCheck();
    }

}
