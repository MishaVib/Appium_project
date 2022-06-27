package drivers;
import com.codeborne.selenide.WebDriverProvider;

import config.Credentials;
import helpers.Browserstack;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    public static URL getBrowserstackUrl() {
        try {
            return new URL(Credentials.config.url());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        mutableCapabilities.setCapability("browserstack.appium_version", "1.22.0");
        mutableCapabilities.setCapability("browserstack.user", Browserstack.browserstackLogin);
        mutableCapabilities.setCapability("browserstack.key", Browserstack.browserstackPassword);
        mutableCapabilities.setCapability("app", Credentials.config.app());
        mutableCapabilities.setCapability("device", Credentials.config.deviceName());
        mutableCapabilities.setCapability("os_version", Credentials.config.platformVersion());
        mutableCapabilities.setCapability("project", "BrowserStack_project");
        mutableCapabilities.setCapability("build", "browserstack-version");
        mutableCapabilities.setCapability("name", "Android_run");

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }
}
