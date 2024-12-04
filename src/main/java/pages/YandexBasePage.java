package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.CustomUtilities;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;

public class YandexBasePage {

    private String buttonByTextXpath = "//button[text()='%1$s' or .//text()='%1$s']";
    private String linkByTextXpath = "//a[text()='%1$s' or .//text()='%1$s']";
    private String inputByPlaceholderXpath = "//input[@placeholder='%s']";

    @Step("Открыть главную страницу сайта 'Яндекс'")
    public <T extends YandexBasePage> T openYandexMainPage(Class<T> nextType) {
        open("https://ya.ru/");
        return nextType.cast(page(nextType));
    }

    @Step("Нажать кнопку с текстом '{buttonText}'")
    public <T extends YandexBasePage> T clickButton(String buttonText, Class<T> nextType) {
        SelenideElement buttonElement = $x(String.format(buttonByTextXpath, buttonText)).should(clickable);
        CustomUtilities.screenshotElement(buttonElement);
        buttonElement.click();
        return nextType.cast(page(nextType));
    }

    @Step("Нажать ссылку с текстом '{linkText}'")
    public <T extends YandexBasePage> T clickLink(String linkText, Class<T> nextType) {
        SelenideElement linkElement = $x(String.format(linkByTextXpath, linkText)).should(clickable);
        CustomUtilities.screenshotElement(linkElement);
        linkElement.click();
        return nextType.cast(page(nextType));
    }

    @Step("Ввести текст '{text}' в поле с placeholder = '{placeholderValue}'")
    public <T extends YandexBasePage> T sendKeysToInputByPlaceholder(String placeholderValue, String text, Class<T> nextType) {
        SelenideElement inputElement = $x(String.format(inputByPlaceholderXpath, placeholderValue)).should(enabled);
        inputElement.clear();
        inputElement.sendKeys(text);
        CustomUtilities.screenshotElement(inputElement);
        return nextType.cast(page(nextType));
    }
}
