package mobileTests.Browserstack;

import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Tag;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

@Tag("browserStack")
public class UITests extends TestBase {
    void newSearchTest() {
        step("Скип приветствия", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());
        step("Тап на инпут search c заполнением поля", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("BrowserStack");
        });
        step("результаты поиска", () ->
                $$(byClassName("android.widget.TextView")).shouldHave(sizeGreaterThan(0)));
    }

}
