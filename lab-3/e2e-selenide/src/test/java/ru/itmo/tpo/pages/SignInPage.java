package ru.itmo.tpo.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SignInPage {
    public static final String URL = "https://www.multitran.com/m.exe?a=40/";

    private final SelenideElement loginInput = $x("//tr[1]/td[2]/input");
    private final SelenideElement passwordInput = $x("//tr[2]/td[2]/input");
    private final SelenideElement signInButton = $x("//tr[4]/td/input");
    private final SelenideElement invalidPasswordWarning = $x("/html/body/div[1]/div[5]/table");

    public void login(String username, String password) {
        loginInput.setValue(username);
        passwordInput.setValue(password);
        signInButton.click();
    }

    public boolean hasInvalidPasswordWarning() {
        return invalidPasswordWarning.isDisplayed();
    }
}
