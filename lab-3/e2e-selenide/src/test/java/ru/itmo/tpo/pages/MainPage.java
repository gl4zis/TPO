package ru.itmo.tpo.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public static final String URL = "https://www.multitran.com/m.exe";

    private final SelenideElement acceptCookieButton = $x("//button[@id='ez-accept-all']");

    private final SelenideElement signInButton = $x("//a[@href='/m.exe?a=40']");
    private final SelenideElement signOutButton = $x("//a[contains(@href, 'doit')]");
    private final SelenideElement profileButton = $x("//a[contains(@href, '/m.exe?a=116')]");

    public void open() {
        Selenide.open(URL);
        acceptCookie();
    }

    public void goToSignInPage() {
        signInButton.click();
    }

    public void signOut() {
        signOutButton.click();
    }

    public boolean isUserAuthorized() {
        return profileButton.isDisplayed();
    }

    private void acceptCookie() {
        if (acceptCookieButton.exists() && acceptCookieButton.isDisplayed()) {
            acceptCookieButton.click();
        }
    }
}
