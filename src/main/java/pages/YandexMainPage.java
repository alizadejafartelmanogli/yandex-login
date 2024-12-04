package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static helpers.CustomUtilities.screenshot;
import static helpers.CustomUtilities.waitForElement;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexMainPage extends YandexBasePage {

    private String authDoneUserIconXpath = "//a[@aria-label='Профиль, вход выполнен']";

    @Step("Проверить справа вверху страницы появление иконки с профилем пользователя")
    public <T extends YandexMainPage> T checkAuthDoneUserIcon(Class<T> nextType) {
        SelenideElement iconElement = $x(authDoneUserIconXpath);
        boolean iconIsVisible = waitForElement(iconElement);
        screenshot(iconElement);
        assertTrue(iconIsVisible, "На странице не появилось иконки с профилем пользователя.");
        return nextType.cast(page(nextType));
    }
}
