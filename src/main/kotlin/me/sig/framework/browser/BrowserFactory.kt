package me.sig.framework.browser

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit

class BrowserFactory {

    private lateinit var driver: WebDriver

    fun createDriver(browserType: BrowserType): BrowserFactory {
        this.driver = when (browserType) {
            BrowserType.Firefox -> {
                WebDriverManager.firefoxdriver().setup()
                FirefoxDriver()
            }

            BrowserType.Chrome -> {
                WebDriverManager.chromedriver().setup()
                ChromeDriver()
            }

        }
        this.driver.manage().window().maximize()
        this.driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS)
        return this
    }

    fun getDriverInstance(): WebDriver {
        return this.driver
    }

}
