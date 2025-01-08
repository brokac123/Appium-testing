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


public class AddReviewTest {

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

        MobileElement searchBar2= driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.EditText");
        searchBar2.click();
        searchBar2.sendKeys("Enis Bešlagić");
        Thread.sleep(5000);

        MobileElement gameReboot= driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View");
        gameReboot.click();

        MobileElement star= driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[4]");
        star.click();
        Thread.sleep(2000);
        MobileElement starButton= driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[6]/android.widget.Button");
        starButton.click();
        Thread.sleep(2000);
        MobileElement description=driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]");
        description.click();
        description.sendKeys("Svidjelo mi se, ali nije bilo nista specijalno");

        Thread.sleep(5000);

        driver.quit();
    }

}
