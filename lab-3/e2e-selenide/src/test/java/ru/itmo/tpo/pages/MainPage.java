package ru.itmo.tpo.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage extends CookiePage {
    public static final String URL = "https://www.multitran.com";

    private final SelenideElement signInButton = $x("//a[@href='/m.exe?a=40']");
    private final SelenideElement signOutButton = $x("//a[contains(@href, 'doit')]");
    private final SelenideElement profileButton = $x("//a[contains(@href, '/m.exe?a=116')]");
    private final SelenideElement languageButton = $x("//a[2]");
    private final SelenideElement popularDict = $x("//*[@id=\"start\"]/div[5]/table/tbody/tr/td[1]/div/b");
    private final SelenideElement newDict = $x("//*[@id=\"start\"]/div[5]/table/tbody/tr/td[3]/b");
    private final SelenideElement langs = $x("//*[@id=\"start\"]/div[5]/table/tbody/tr/td[3]/div[2]/b");
    private final SelenideElement engRusDictLink = $x("//a[@href='/m.exe?l1=1&l2=2']");

    @Step("Open main Multitran page")
    public void open() {
        Selenide.open(URL);
        acceptCookie();
    }

    @Step("Go to sign in page")
    public void goToSignInPage() {
        signInButton.click();
    }

    @Step("Sign out from account")
    public void signOut() {
        signOutButton.click();
    }

    @Step("Check is user authorized")
    public boolean isUserAuthorized() {
        return profileButton.isDisplayed();
    }

    @Step("Got to language page")
    public void goToLanguagePage() {
        languageButton.click();
    }

    @Step("Check chosen interface language")
    public String getCurrentLanguage() {
        return languageButton.getText();
    }

    @Step("Go to English-Russian dictionary")
    public void goToEngRusDictionary() {
        engRusDictLink.click();
    }

    @Step("Get popular dictionaries text")
    public String getPopularDictionariesText() {
        return popularDict.getText();
    }

    @Step("Get new dictionaries text")
    public String getNewDictionariesText() {
        return newDict.getText();
    }

    @Step("Get languages text")
    public String getLanguagesText() {
        return langs.getText();
    }
}
