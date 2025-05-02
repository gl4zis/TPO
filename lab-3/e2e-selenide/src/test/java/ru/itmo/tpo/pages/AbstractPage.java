package ru.itmo.tpo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public abstract class AbstractPage {
    private final SelenideElement acceptCookieButton = $x("//button[@id='ez-accept-all']");

    @Step("Check if page contains text")
    public boolean containsText(String text) {
        return $("body").getText().contains(text);
    }

    @Step("Check if page contains link with text")
    public boolean containsLinkWithText(String text) {
        return $x("//a[contains(text(), '" + text + "')]").isDisplayed();
    }

    @Step("Go to link")
    public void goToLinkWithText(String text) {
        $x("//a[contains(text(), '" + text + "')]").click();
    }

    protected void acceptCookie() {
        if (acceptCookieButton.isDisplayed()) {
            acceptCookieButton.click();
        }
    }
}
