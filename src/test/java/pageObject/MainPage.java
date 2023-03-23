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

    public void fieldInfo(String card) {
        var correctData = DataHelper.card();

        informationFieldInput.get(0).setValue(card);
        informationFieldInput.get(1).setValue(String.valueOf((correctData.getMonth())));
        informationFieldInput.get(2).setValue(String.valueOf((correctData.getYear())));
        informationFieldInput.get(3).setValue(String.valueOf((correctData.getHolder())));
        informationFieldInput.get(4).setValue(String.valueOf((correctData.getPin())));
    }

    public void numberField(String value) {
        informationFieldInput.get(0).setValue(value);
    }

    public void monthField(String value) {
        informationFieldInput.get(1).setValue(value);
    }

    public void yearField(String value) {
        informationFieldInput.get(2).setValue(value);
    }

    public void holderField(String value) {
        informationFieldInput.get(3).setValue(value);
    }

    public void pinField(String value) {
        informationFieldInput.get(4).setValue(value);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void successCheck() {
        successNotification.should(Condition.appear, Duration.ofMillis(15000));
    }

    public void failCheck() {failNotification.should(Condition.appear, Duration.ofMillis(15000));}

    public MainPageErrors link(){
        return new MainPageErrors();
    }

}