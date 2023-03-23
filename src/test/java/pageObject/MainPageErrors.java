package pageObject;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class MainPageErrors {
    private SelenideElement incorrectFormat = $x("//span[contains(text(),'формат')]");
    private SelenideElement wrongMonth = $x("//span[contains(text(),'Неверно указан')]");
    private SelenideElement wrongYear = $x("//span[contains(text(),'Истёк срок')]");
    private SelenideElement emptyHolder = $x("//span[contains(text(),'для заполнения')]");

    public String getIncorrectFormat() {
        return incorrectFormat.getText();
    }

    public String getWrongMonth() {
        return wrongMonth.getText();
    }

    public String getWrongYear() {
        return wrongYear.getText();
    }

    public String getEmptyHolder() {
        return emptyHolder.getText();
    }
}
