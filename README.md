# Required Software Tools
<li>Appium server</li>
<li>Appium inspector</li>
<li>Android Studio (for run emulator)</li>
<li>Intellij IDEA for Maven project</li>

# Appium Mobile Test
<h3>Mobile testing using appium server and maven project</h3>
<li>1-Start appium server</li>

<li>2-Start appium inspector (to see the skeleton of the application)
    
        deviceName       :  your device name or emulator name 
        platformName     :  using platform name (android or ios)
        udid             :  the id of the device connected to the computer
            open cmd ->  adb devices (lists devices connected to the computer) exp: device-5554
        platformVersion  :  device platform version
        appPackage       :  Package name used
        appActivity      :  Activity name used
            open cmd ->  open your apk file you want to test on emulator or device
                         adb shell
                         dumpsys window windows | grep –E ‘mCurrentFocus’ 
                    exp: com.google.android.deskclock/com.android.deskclock.DeskClock 
                         left side from the sign means your package name
                         right side from the sign means your activity name

</li>

<li>3-Define device information in maven project (@BeforeMethod)
    
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

</li>

<li>4-Write test codes (@test)
  
  
       @test
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
</li>

<li>5-Write after test codes (@AfterMethod)
    
        @AfterMethod
        public void cik(){
            driver.quit();
        }

</li>

<li>6-You are ready to run your test code :))</li>
