package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class LoginTest {

    static AppiumDriver<MobileElement> driver = null;

    @BeforeAll
    public static void setUpDriver() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("VERSION","9.0");
        caps.setCapability("deviceName", "ce11171b8c68a63f05"); // Tvoj uređaj ili emulator
        caps.setCapability("app", "D:/p/hadoop.apk"); // Putanja do tvoje APK aplikacije

        // Pokretanje AndroidDriver-a
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public static void performLogin() throws InterruptedException {
        if (driver == null) {
            System.err.println("Driver nije inicijaliziran!");
            return;
        }

        MobileElement loginButton= driver.findElementByAccessibilityId("navigateToLoginButton");
        loginButton.click();
        Thread.sleep(2000);

        MobileElement userEmail= driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.EditText[1]");
        userEmail.clear();
        userEmail.sendKeys("trile@gmail.com");
        Thread.sleep(2000);

        MobileElement userPassword= driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.EditText[2]");
        userPassword.clear();
        userPassword.sendKeys("44445555");
        Thread.sleep(2000);

        MobileElement login=driver.findElementByAccessibilityId("logInButton");
        login.click();
        Thread.sleep(10000);

    //    System.out.println("Korisnik  uspješno prijavio");
      //  driver.quit();
    }
}
