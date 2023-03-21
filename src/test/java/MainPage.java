import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private SelenideElement headline = $x("//h2");
    private SelenideElement picture = $x("//img");
    private SelenideElement buying = $x("//button/span/*[text()='Купить']");
    private SelenideElement credit = $x("//button/span/*[contains(text(), 'кредит')]");
    private SelenideElement cardNumber = $("form span input");
    private SelenideElement monthField = $x("//form//div/span//*[text()='Месяц']");
    private SelenideElement yearField = $x("//form//div/span//*[text()='Год']");
    private SelenideElement cardHolder = $x("//form//div/span//*[text()='Владелец']");
    private SelenideElement pinCode = $x("//form//div/span//*[text()='CVC/CVV']");
    private SelenideElement buyingHeadline = $x("//h3[contains(text(), 'карт')]");
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
        buyingHeadline.should(Condition.appear);
        cardNumber.setValue("4444 4444 4444 4442");
        monthField.setValue("08");
        yearField.setValue("23");
        cardHolder.setValue("RRR FFF");
        pinCode.setValue("999");
        continueButton.click();
        successNotification.should(Condition.appear);
    }

}