package ru.itmo.tpo.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.tpo.pages.MainPage;
import ru.itmo.tpo.pages.SignInPage;

@Epic("E2E Selenide")
@Feature("Login")
public class LoginTest {
    private static final String TEST_LOGIN = "gl4zis";
    private static final String TEST_PASSWORD = "niaeHApgcBAA";

    private MainPage mainPage;
    private SignInPage signInPage;

    @BeforeAll
    public static void beforeAll() {
        Configuration.headless = true;
    }

    @BeforeEach
    public void setUp() {
        mainPage = new MainPage();
        signInPage = new SignInPage();
        mainPage.open();

        if (mainPage.isUserAuthorized()) {
            mainPage.signOut();
        }
    }

    @Test
    @Story("Successful login into test account")
    public void successfulLogin() {
        mainPage.goToSignInPage();
        signInPage.login(TEST_LOGIN, TEST_PASSWORD);

        Assertions.assertTrue(mainPage.isUserAuthorized());
    }

    @Test
    @Story("Try to login with invalid password")
    public void invalidPasswordLogin() {
        mainPage.goToSignInPage();
        signInPage.login(TEST_LOGIN, "some_invalid_password");

        Assertions.assertTrue(signInPage.hasInvalidPasswordWarning());
    }
}
