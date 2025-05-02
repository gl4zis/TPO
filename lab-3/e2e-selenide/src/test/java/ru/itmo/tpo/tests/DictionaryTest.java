package ru.itmo.tpo.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DictionaryTest extends BaseUiTest {
    @BeforeEach
    public void setUp() {
        if (!mainPage.getCurrentLanguage().equals("English")) {
            mainPage.goToLanguagePage();
            languagePage.chooseEnglish();
        }
    }

    @Test
    @Story("Go to English-Russian dictionary terms")
    public void goToDictionaryTerms() {
        mainPage.goToLinkWithText("English‑Russian");

        Assertions.assertTrue(dictionaryTermsPage.containsText("English-Russian"));
        Assertions.assertTrue(dictionaryTermsPage.containsLinkWithText("Aerodynamics"));
    }
}
