import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

public class CalculatorTest extends WebDriverTestCase {
    private AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2"); // This is important
        capabilities.setCapability("appPackage", "com.android.calculator2");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @Test
    public void testAddition() {
        MobileElement two = driver.findElementById("com.android.calculator2:id/digit_2");
        MobileElement plus = driver.findElementByAccessibilityId("plus");
        MobileElement three = driver.findElementById("com.android.calculator2:id/digit_3");
        MobileElement equals = driver.findElementByAccessibilityId("equals");
        MobileElement result = driver.findElementById("com.android.calculator2:id/result");

        two.click();
        plus.click();
        three.click();
        equals.click();

        assert result.getText().equals("5") : "Test failed!";
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

