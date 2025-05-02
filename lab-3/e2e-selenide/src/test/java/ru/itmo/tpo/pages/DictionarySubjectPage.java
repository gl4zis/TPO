package ru.itmo.tpo.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class DictionarySubjectPage {
    private final SelenideElement dictionaryName =
            $x("//*[@id=\"start\"]/div[5]/table/tbody/tr/td[1]/table[1]/tbody/tr/td[1]/b");
    private final SelenideElement sortByEntriesButton =
            $x("//*[@id='start']/div[5]/table/tbody/tr/td[1]/table[2]/tbody/tr[1]/td[2]/b/a");
    private final SelenideElement sortByNameButton =
            $x("//*[@id='start']/div[5]/table/tbody/tr/td[1]/table[2]/tbody/tr[1]/td[1]/b/a");
    private final SelenideElement firstSubjectLink =
            $x("//*[@id='start']/div[5]/table/tbody/tr/td[1]/table[2]/tbody/tr[2]/td[1]/a");
    private final SelenideElement userAddedTermsHeader =
            $x("//*[@id=\"start\"]/div[5]/table/tbody/tr/td[3]/table/tbody/tr[2]/td/b");
    private final SelenideElement subjectTable =
            $x("//*[@id=\"start\"]/div[5]/table/tbody/tr/td[1]/table[2]/tbody");
    private final SelenideElement userTable =
            $x("//*[@id=\"start\"]/div[5]/table/tbody/tr/td[3]/table/tbody");

    @Step("Get name of the dictionary")
    public String getDictionaryName() {
        return dictionaryName.getText().split(" ")[0];
    }

    @Step("Go to dictionary subject")
    public void goToSubject(String subject) {
        $x("//a[contains(text(), '" + subject + "')]").click(ClickOptions.usingJavaScript());
    }

    @Step("Sort subjects by entries")
    public void sortSubjectsByEntries() {
        sortByEntriesButton.click();
    }

    @Step("Sort subjects by name")
    public void sortSubjectsByName() {
        sortByNameButton.click();
    }

    @Step("Check name of first subject in a list")
    public boolean checkFirstSubjectInList(String name) {
        return firstSubjectLink.getText().equals(name);
    }

    @Step("Check if block with user-added terms exists")
    public boolean hasUserAddedTerms() {
        return "Terms added by users".equals(userAddedTermsHeader.getText());
    }

    @Step("Find link to subject")
    public boolean hasLinkToSubject(String subject) {
        return subjectTable.find(By.xpath(".//a[contains(text(), '" + subject + "')]"))
                .isDisplayed();
    }

    @Step("Find user in user table")
    public boolean hasUser(String username) {
        return userTable.find(By.xpath(".//a[contains(text(), '" + username + "')]"))
                .isDisplayed();
    }
}
