import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class ServiceTest {
    MainPage main;
    @BeforeEach
    void openChrome() { main = open("http://localhost:8080/", MainPage.class);}

    @Test
    void test123() {
        main.buyingProcess();
    }
}
