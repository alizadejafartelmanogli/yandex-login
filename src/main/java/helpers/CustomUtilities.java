package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;
import static java.time.Duration.ofSeconds;

public class CustomUtilities {

    @Attachment(type = "image/png")
    public static byte[] screenshotElement(SelenideElement element) {
        String js = "function mark(element){" +
                "function removeMark(el){el.setAttribute('style','');}" +
                "element.setAttribute('style','border: 3px solid red;');" +
                "setTimeout(removeMark,700,element);};" +
                "mark(arguments[0])";
        try {
            element.should(exist, ofSeconds(3));
            executeJavaScript(js, element);
            sleep(50);
        } catch (UIAssertionError | WebDriverException ignored) {
        }
        return Base64.getDecoder().decode(Selenide.screenshot(OutputType.BASE64));
    }

    @Attachment(type = "image/png")
    public static byte[] screenshotPage() {
        String screenshot = Selenide
                .screenshot("screen_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")))
                .replace("file:/", "")
                .replaceAll("%20", " ");
        try {
            return Files.readAllBytes(Paths.get(screenshot));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static byte[] screenshot(SelenideElement element) {
        if (element.is(visible))
            return screenshotElement(element);
        else
            return screenshotPage();
    }

    public static String initEnv(String envName, String defaultValue) {
        String env = System.getenv(envName);
        if (env == null)
            env = System.getProperty(envName, defaultValue);
        return env;
    }

    public static boolean waitForElement(SelenideElement element) {
        for (int i = 0; i < 10; i++) {
            if (element.is(visible))
                return true;
            sleep(1000);
        }
        return false;
    }
}
