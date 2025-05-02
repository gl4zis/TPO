package ru.itmo.tpo.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public abstract class CookiePage {
    private final SelenideElement acceptCookieButton = $x("//button[@id='ez-accept-all']");

    protected void acceptCookie() {
        if (acceptCookieButton.isDisplayed()) {
            acceptCookieButton.click();
        }
    }
}
