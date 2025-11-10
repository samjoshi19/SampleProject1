package com.example.tests;

import com.example.pages.LoginPage;
import com.example.utils.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
    @Test
    public void validLoginShowsSuccessMessage() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.enterUsername("tomsmith");
        login.enterPassword("SuperSecretPassword!");
        login.clickLogin();
        Thread.sleep(1000);
        String flash = login.getFlashText();
        Assertions.assertTrue(flash.contains("You logged into a secure area!"));
    }
}
