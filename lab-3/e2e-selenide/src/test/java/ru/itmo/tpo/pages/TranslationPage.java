package ru.itmo.tpo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class TranslationPage {
    private final SelenideElement searchInput = $x("//input[@name='s']");
    private final SelenideElement resultTable = $x("//*[@id='start']/div[5]/table[1]/tbody");

    @Step("Get search input value")
    public String getInputValue() {
        return searchInput.getValue();
    }

    @Step("Find translation result")
    public boolean hasResult(String result) {
        return resultTable.find(By.xpath(".//a[contains(text(), '" + result + "')]"))
                .isDisplayed();
    }
}
