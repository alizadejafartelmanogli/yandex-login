package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static helpers.CustomUtilities.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexPassportPage extends YandexBasePage {

    private String passportInputXpath = "//input[@placeholder='Введите пароль']";
    private String stateErrorMessage = "//*[contains(@class,'state_error') and text()='%s']";

    @Step("Установить пароль в поле ввода")
    public <T extends YandexBasePage> T sendKeysPassword(Class<T> nextType) {
        SelenideElement inputElement = $x(passportInputXpath).should(enabled);
        inputElement.clear();
        inputElement.sendKeys(initEnv("YANDEX.PASSWORD", ""));
        screenshotElement(inputElement);
        return nextType.cast(page(nextType));
    }

    @Step("Проверить появление сообщения ошибки с текстом: {message}")
    public <T extends YandexBasePage> T checkStateErrorMessage(String message, Class<T> nextType) {
        SelenideElement messageElement = $x(String.format(stateErrorMessage, message));
        boolean messageIsVisible = waitForElement(messageElement);
        screenshot(messageElement);
        assertTrue(messageIsVisible, "На странице не появилось сообщение с ошибкой: " + message);
        return nextType.cast(page(nextType));
    }
}
