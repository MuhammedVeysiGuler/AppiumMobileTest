package Appium.example;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppTest{

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;


    @BeforeMethod
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel 4");
        caps.setCapability("udid", "emulator-5554");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("appPackage", "com.google.android.deskclock");
        caps.setCapability("appActivity","com.android.deskclock.DeskClock");
        caps.setCapability("noReset","false");
        driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void alarmKur(){
        WebElement alarm = driver.findElementByXPath("" +
                "\t\n" +
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.view.ViewGroup/android.widget.HorizontalScrollView/android.widget.LinearLayout/za[1]/android.widget.TextView" +
                "");  //5
        alarm.click();

        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"8:30 AM\"]").click();
        driver.findElementByXPath("\t\n" +
                "//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"10\"]").click();
        driver.findElementByXPath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"55\"]").click();
        driver.findElementByXPath("\t\n" +
                "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.TimePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[2]").click();
        driver.findElementByXPath("\t\n" +
                "//android.widget.TextView[@content-desc=\"10:55 AM\"]");

        System.out.println("Alarm başarıyla eklendi");


        //android.widget.TextView[@content-desc="8:30 AM"]
    }

    @Test
    public void alarmKapat_Ac(){
        WebElement alarm = driver.findElementByXPath("" +
                "\t\n" +
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.view.ViewGroup/android.widget.HorizontalScrollView/android.widget.LinearLayout/za[1]/android.widget.TextView" +
                "");
        alarm.click();

        WebElement ac_kapat = driver.findElementByXPath("" +
                "\t\n" +
                "//android.view.ViewGroup[@content-desc=\"8:30 AM Alarm\"]/android.widget.Switch" +
                "");  //5
        ac_kapat.click();
        String deger = driver.findElementByXPath("" +
                "\t\n" +
                "//android.view.ViewGroup[@content-desc=\"8:30 AM Alarm\"]/android.widget.Switch" +
                "").getText();
        if ( deger.equals("ON")){
            System.out.println("Alarm Açıldı");
        }else{
            System.out.println("Alarm Kapatıldı");
        }
    }

    @AfterMethod
    public void cik(){
        driver.quit();
    }
}