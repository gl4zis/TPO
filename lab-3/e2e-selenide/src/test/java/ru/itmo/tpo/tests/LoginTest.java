package ru.itmo.tpo.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Feature("Login")
public class LoginTest extends BaseUiTest {
    private static final String TEST_LOGIN = "gl4zis";
    private static final String TEST_PASSWORD = "niaeHApgcBAA";

    @BeforeEach
    public void setUp() {
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
