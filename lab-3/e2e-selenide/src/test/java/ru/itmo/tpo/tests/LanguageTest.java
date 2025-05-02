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

        Assertions.assertEquals("Popular dictionaries", mainPage.getPopularDictionariesText());
        Assertions.assertEquals("New dictionaries", mainPage.getNewDictionariesText());
        Assertions.assertEquals("Languages", mainPage.getLanguagesText());
    }

    @Test
    @Story("Choose interface language to russian")
    public void setupRussianInterface() {
        mainPage.goToLanguagePage();
        languagePage.chooseRussian();

        Assertions.assertEquals("Популярные словари", mainPage.getPopularDictionariesText());
        Assertions.assertEquals("Новые словари", mainPage.getNewDictionariesText());
        Assertions.assertEquals("Языки", mainPage.getLanguagesText());
    }
}
