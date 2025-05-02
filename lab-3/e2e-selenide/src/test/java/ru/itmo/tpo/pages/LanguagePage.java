package ru.itmo.tpo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LanguagePage {
    private final SelenideElement englishButton = $x("//div[5]/table/tbody/tr/td/a");
    private final SelenideElement russianButton = $x("//div[5]/table/tbody/tr[2]/td/a");

    @Step("Choose english language")
    public void chooseEnglish() {
        englishButton.click();
    }

    @Step("Choose russian language")
    public void chooseRussian() {
        russianButton.click();
    }
}
