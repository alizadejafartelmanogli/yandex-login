import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.YandexBasePage;
import pages.YandexMainPage;
import pages.YandexPassportPage;

import static helpers.CustomUtilities.initEnv;

public class YandexLoginTest extends WebConfig {

    @Owner("Aliev Jafar")
    @DisplayName("Аутентификация на сайте 'Яндекс' с валидными логином и паролем")
    @Test
    public void authWithValidLoginAndPassword() {
        new YandexBasePage()
                .openYandexMainPage(YandexMainPage.class)
                .clickLink("Войти", YandexPassportPage.class)
                .sendKeysToInputByPlaceholder("Логин или email", initEnv("YANDEX.LOGIN", ""), YandexPassportPage.class)
                .clickButton("Войти", YandexPassportPage.class)
                .sendKeysPassword(YandexPassportPage.class)
                .clickButton("Продолжить", YandexMainPage.class)
                .checkAuthDoneUserIcon(YandexMainPage.class);
    }

    @Owner("Aliev Jafar")
    @DisplayName("Аутентификация на сайте 'Яндекс' с невалидным логином")
    @Test
    public void authWithInvalidLogin() {
        new YandexBasePage()
                .openYandexMainPage(YandexMainPage.class)
                .clickLink("Войти", YandexPassportPage.class)
                .sendKeysToInputByPlaceholder("Логин или email", initEnv("YANDEX.LOGIN", ""), YandexPassportPage.class)
                .clickButton("Войти", YandexPassportPage.class)
                .checkStateErrorMessage("Нет такого аккаунта. Проверьте логин или войдите по\u00A0телефону", YandexPassportPage.class);
    }

    @Owner("Aliev Jafar")
    @DisplayName("Аутентификация на сайте 'Яндекс' с некорректным логином")
    @Test
    public void authWithIncorrectLogin() {
        new YandexBasePage()
                .openYandexMainPage(YandexMainPage.class)
                .clickLink("Войти", YandexPassportPage.class)
                .sendKeysToInputByPlaceholder("Логин или email", initEnv("YANDEX.LOGIN", ""), YandexPassportPage.class)
                .clickButton("Войти", YandexPassportPage.class)
                .checkStateErrorMessage("Такой логин не\u00A0подойдет", YandexPassportPage.class);
    }

    @Owner("Aliev Jafar")
    @DisplayName("Аутентификация на сайте 'Яндекс' с пустым логином")
    @Test
    public void authWithEmptyLogin() {
        new YandexBasePage()
                .openYandexMainPage(YandexMainPage.class)
                .clickLink("Войти", YandexPassportPage.class)
                .sendKeysToInputByPlaceholder("Логин или email", initEnv("YANDEX.LOGIN", ""), YandexPassportPage.class)
                .clickButton("Войти", YandexPassportPage.class)
                .checkStateErrorMessage("Логин не\u00A0указан", YandexPassportPage.class);
    }

    @Owner("Aliev Jafar")
    @DisplayName("Аутентификация на сайте 'Яндекс' с невалидными паролем")
    @Test
    public void authWithInvalidPassword() {
        new YandexBasePage()
                .openYandexMainPage(YandexMainPage.class)
                .clickLink("Войти", YandexPassportPage.class)
                .sendKeysToInputByPlaceholder("Логин или email", initEnv("YANDEX.LOGIN", ""), YandexPassportPage.class)
                .clickButton("Войти", YandexPassportPage.class)
                .sendKeysPassword(YandexPassportPage.class)
                .clickButton("Продолжить", YandexPassportPage.class)
                .checkStateErrorMessage("Неверный пароль", YandexPassportPage.class);
    }

    @Owner("Aliev Jafar")
    @DisplayName("Аутентификация на сайте 'Яндекс' с пустым паролем")
    @Test
    public void authWitEmptyPassword() {
        new YandexBasePage()
                .openYandexMainPage(YandexMainPage.class)
                .clickLink("Войти", YandexPassportPage.class)
                .sendKeysToInputByPlaceholder("Логин или email", initEnv("YANDEX.LOGIN", ""), YandexPassportPage.class)
                .clickButton("Войти", YandexPassportPage.class)
                .sendKeysPassword(YandexPassportPage.class)
                .clickButton("Продолжить", YandexPassportPage.class)
                .checkStateErrorMessage("Пароль не\u00A0указан", YandexPassportPage.class);
    }
}
