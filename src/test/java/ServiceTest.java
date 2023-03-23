import info.DataHelper;
import info.DatabaseProcess;
import org.junit.jupiter.api.AfterAll;
import pageObject.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static com.codeborne.selenide.Selenide.open;

public class ServiceTest {
    MainPage main;
    @BeforeEach
    void openChrome() {
        main = open("http://localhost:8080/", MainPage.class);
    }

    @AfterAll
    static void clear() {
        try {
            DatabaseProcess.clean();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
