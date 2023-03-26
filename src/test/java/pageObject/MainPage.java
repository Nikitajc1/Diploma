package pageObject;

import info.DataHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private SelenideElement headline = $x("//h2");
    private SelenideElement picture = $x("//img");
    private SelenideElement buying = $x("//button/span/*[text()='Купить']");
    private SelenideElement credit = $x("//button/span/*[contains(text(), 'кредит')]");
    private ElementsCollection informationFieldInput = $$("form span input");
    private SelenideElement successNotification = $x("//div/div[contains(@class, 'notification__title')] [text()='Успешно']");
    private SelenideElement failNotification = $x("//div/div[contains(@class, 'notification__title')] [text()='Ошибка']");
    private SelenideElement continueButton = $x("//form//div/button");

    public void visibleHeadline() {
        headline.shouldBe(Condition.visible);
    }

    public void visiblePicture() {
        picture.shouldBe(Condition.visible);
    }

    public void buyingProcess() {
        buying.click();
    }

    public void creditProcess() {
        credit.click();
    }

    public void fieldInfo(String card, String month, String year, String holder, String pin) {

        informationFieldInput.get(0).setValue(card);
        informationFieldInput.get(1).setValue(month);
        informationFieldInput.get(2).setValue(year);
        informationFieldInput.get(3).setValue(holder);
        informationFieldInput.get(4).setValue(pin);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void successCheck() {
        successNotification.should(Condition.visible, Duration.ofMillis(15000));
    }

    public void failCheck() {failNotification.should(Condition.visible, Duration.ofMillis(15000));}

    public MainPageErrors link(){
        return new MainPageErrors();
    }

}