package mobileTests.appium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.Credentials;
import drivers.AppiumMobileDriver;
import drivers.BrowserstackMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attach.getSessionId;
import static io.qameta.allure.Allure.step;


public class TestBase {

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        switch (System.getProperty("device")) {
            case "real":
            case "emulator":
                Configuration.browser = AppiumMobileDriver.class.getName();
                break;
            case "browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            default:
                throw new IllegalArgumentException("Unknown device");
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();  // нужно для запуска с селенидом
    }

    @AfterEach
    public void afterEach() {
        String sessionId = "";
        if (Credentials.isBrowserStack()) {
            sessionId = getSessionId();
        }

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        step("Close driver", Selenide::closeWebDriver);


        if (Credentials.isBrowserStack()) {
            Attach.video(sessionId);
        }
    }

}
