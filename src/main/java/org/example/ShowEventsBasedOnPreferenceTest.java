package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class ShowEventsBasedOnPreferenceTest {

    static AppiumDriver<MobileElement> driver = null;

    @BeforeAll
    public static void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("VERSION","9.0");
        caps.setCapability("deviceName", "ce11171b8c68a63f05"); // Tvoj uređaj ili emulator
        caps.setCapability("app", "D:/p/hadoop.apk"); // Putanja do tvoje APK aplikacije

        // Pokretanje AndroidDriver-a
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LoginTest.setUpDriver();
        driver = LoginTest.driver;

        // Izvrši login
        LoginTest.performLogin();
    }

    @Test
    public void simpleTest() throws InterruptedException {
        if (driver == null) {
            System.err.println("Driver nije inicijaliziran!");
            return;
        }

       MobileElement profileButton= driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[5]/android.view.View[2]\n");

        profileButton.click();
        Thread.sleep(3000);

       MobileElement editPreferences= driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[4]");

       editPreferences.click();
       Thread.sleep(8000);

       MobileElement homeButton= driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View[2]");
       homeButton.click();
        scrollDown();
        Thread.sleep(4000);

        driver.quit();
    }
    public void scrollDown() {
        // Dohvati dimenzije ekrana
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        // Postavi početnu i završnu točku za scroll
        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.8); // Početak pri dnu ekrana
        int endY = (int) (screenHeight * 0.2); // Kraj blizu vrha ekrana

        // Izvrši scroll
        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY)) // Početna točka
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) // Vrijeme trajanja
                .moveTo(PointOption.point(startX, endY)) // Završna točka
                .release()
                .perform();
    }
}
