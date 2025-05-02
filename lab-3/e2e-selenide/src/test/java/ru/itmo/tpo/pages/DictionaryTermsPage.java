package ru.itmo.tpo.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class DictionaryTermsPage extends AbstractPage {

    @Step("Go to dictionary term")
    public void goToTerm(String term) {
        $x("//a[contains(., '" + term + "')]").click();
    }
}
