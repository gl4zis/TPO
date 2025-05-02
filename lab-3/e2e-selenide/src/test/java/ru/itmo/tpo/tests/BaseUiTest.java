package ru.itmo.tpo.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.itmo.tpo.pages.*;

@Epic("E2E Selenide")
public abstract class BaseUiTest {
    protected MainPage mainPage;
    protected SignInPage signInPage;
    protected LanguagePage languagePage;
    protected DictionarySubjectPage dictionarySubjectPage;
    protected DictionarySubjectTermPage dictionarySubjectTermPage;
    protected TranslationPage translationPage;

    @BeforeAll
    public static void beforeAll() {
        Configuration.headless = true;
    }

    @BeforeEach
    public void beforeEach() {
        mainPage = new MainPage();
        signInPage = new SignInPage();
        languagePage = new LanguagePage();
        dictionarySubjectPage = new DictionarySubjectPage();
        dictionarySubjectTermPage = new DictionarySubjectTermPage();
        translationPage = new TranslationPage();

        mainPage.open();
    }
}
