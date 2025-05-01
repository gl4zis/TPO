package ru.itmo.tpo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.tpo.pages.MainPage;
import ru.itmo.tpo.pages.SignInPage;

public class LoginTest {
    private static final String TEST_LOGIN = "gl4zis";
    private static final String TEST_PASSWORD = "niaeHApgcBAA";

    private MainPage mainPage;
    private SignInPage signInPage;

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
    public void successfulLogin() {
        mainPage.goToSignInPage();
        signInPage.login(TEST_LOGIN, TEST_PASSWORD);

        Assertions.assertTrue(mainPage.isUserAuthorized());
    }

    @Test
    public void invalidPasswordLogin() {
        mainPage.goToSignInPage();
        signInPage.login(TEST_LOGIN, "some_random_password");

        Assertions.assertTrue(signInPage.hasInvalidPasswordWarning());
    }
}
