package ru.itmo.tpo.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Feature("Language")
public class LanguageTest extends BaseUiTest {

    @Test
    @Story("Choose interface language to english")
    public void setupEnglishInterface() {
        mainPage.goToLanguagePage();
        languagePage.chooseEnglish();

        Assertions.assertTrue(mainPage.containsText("Popular dictionaries"));
        Assertions.assertTrue(mainPage.containsText("New dictionaries"));
        Assertions.assertTrue(mainPage.containsText("Languages"));
    }

    @Test
    @Story("Choose interface language to russian")
    public void setupRussianInterface() {
        mainPage.goToLanguagePage();
        languagePage.chooseRussian();

        Assertions.assertTrue(mainPage.containsText("Популярные словари"));
        Assertions.assertTrue(mainPage.containsText("Новые словари"));
        Assertions.assertTrue(mainPage.containsText("Языки"));
    }
}
