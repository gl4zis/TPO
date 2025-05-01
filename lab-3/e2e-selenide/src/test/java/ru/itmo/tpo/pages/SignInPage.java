package ru.itmo.tpo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class SignInPage {
    private final SelenideElement loginInput = $x("//tr[1]/td[2]/input/input");
    private final SelenideElement passwordInput = $x("//tr[2]/td[2]/input");
    private final SelenideElement signInButton = $x("//tr[4]/td/input");
    private final SelenideElement invalidPasswordWarning = $x("/html/body/div[1]/div[5]/table");

    @Step("Fill sign in form and submit")
    public void login(String login, String password) {
        loginInput.setValue(login);
        passwordInput.setValue(password);
        signInButton.click();
    }

    @Step("Check page has invalid password warning")
    public boolean hasInvalidPasswordWarning() {
        return invalidPasswordWarning.isDisplayed();
    }
}
