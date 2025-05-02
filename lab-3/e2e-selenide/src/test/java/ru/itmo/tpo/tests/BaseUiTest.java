package ru.itmo.tpo.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.itmo.tpo.pages.DictionaryTermsPage;
import ru.itmo.tpo.pages.LanguagePage;
import ru.itmo.tpo.pages.MainPage;
import ru.itmo.tpo.pages.SignInPage;

@Epic("E2E Selenide")
public abstract class BaseUiTest {
    protected MainPage mainPage;
    protected SignInPage signInPage;
    protected LanguagePage languagePage;
    protected DictionaryTermsPage dictionaryTermsPage;

    @BeforeAll
    public static void beforeAll() {
        Configuration.headless = true;
    }

    @BeforeEach
    public void beforeEach() {
        mainPage = new MainPage();
        signInPage = new SignInPage();
        languagePage = new LanguagePage();
        dictionaryTermsPage = new DictionaryTermsPage();
        mainPage.open();
    }
}
