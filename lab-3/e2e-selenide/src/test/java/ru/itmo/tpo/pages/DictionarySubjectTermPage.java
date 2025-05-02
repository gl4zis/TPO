package ru.itmo.tpo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class DictionarySubjectTermPage {
    private final SelenideElement currentSubject = $x("//*[@id=\"start\"]/div[5]/table[1]/tbody/tr[3]/td/b");
    private final SelenideElement phraseTable = $x("//*[@id=\"start\"]/div[5]/table[2]/tbody");

    @Step("Go to phrase translation")
    public void goToPhraseTranslation(String phrase) {
        $x("//a[contains(text(), '" + phrase + "')]").click();
    }

    @Step("Get current subject name")
    public String getCurrentSubjectName() {
        return currentSubject.getText();
    }

    @Step("Find link to phrase")
    public boolean hasLinkToPhrase(String phrase) {
        return phraseTable.find(By.xpath(".//a[contains(text(), '" + phrase + "')]"))
                .isDisplayed();
    }
}
