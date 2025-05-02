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
    @Story("Go to English-Russian dictionary subjects")
    public void goToDictionarySubjects() {
        mainPage.goToEngRusDictionary();

        Assertions.assertEquals("English-Russian", dictionarySubjectPage.getDictionaryName());
        Assertions.assertTrue(dictionarySubjectPage.hasLinkToSubject("Aerodynamics"));
    }

    @Test
    @Story("Go to dictionary subject terms")
    public void goToDictionarySubjectTerms() {
        mainPage.goToEngRusDictionary();
        dictionarySubjectPage.goToSubject("Aerodynamics");

        Assertions.assertEquals("Aerodynamics", dictionarySubjectTermPage.getCurrentSubjectName());
        Assertions.assertTrue(dictionarySubjectTermPage.hasLinkToPhrase("A/A"));
    }

    @Test
    @Story("Go to translation from dictionary")
    public void goToTranslationFromDictionary() {
        mainPage.goToEngRusDictionary();
        dictionarySubjectPage.goToSubject("Aerodynamics");
        dictionarySubjectTermPage.goToPhraseTranslation("A/A");

        Assertions.assertEquals("A/A", translationPage.getInputValue());
        Assertions.assertTrue(translationPage.hasResult("угол атаки"));
    }

    @Test
    @Story("Sort dictionary subjects by entries")
    public void sortDictionarySubjectsByEntries() {
        mainPage.goToEngRusDictionary();
        dictionarySubjectPage.sortSubjectsByEntries();

        Assertions.assertTrue(dictionarySubjectPage.checkFirstSubjectInList("General"));
    }

    @Test
    @Story("Sort dictionary subjects by name")
    public void sortDictionarySubjectsByName() {
        mainPage.goToEngRusDictionary();
        dictionarySubjectPage.sortSubjectsByEntries();
        dictionarySubjectPage.sortSubjectsByName();

        Assertions.assertTrue(dictionarySubjectPage.checkFirstSubjectInList("Одобренный термин"));
    }

    @Test
    @Story("Check user-added terms")
    public void checkUserAddedTerms() {
        mainPage.goToEngRusDictionary();

        Assertions.assertTrue(dictionarySubjectPage.hasUserAddedTerms());
        Assertions.assertTrue(dictionarySubjectPage.hasUser("Gruzovik"));
    }
}
