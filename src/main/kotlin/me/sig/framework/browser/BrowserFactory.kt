package me.sig.framework.browser

import io.github.bonigarcia.wdm.WebDriverManager
import me.sig.framework.settings.BROWSER
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit

object BrowserFactory {

    private val driver: WebDriver

    init {
        val browserType = BrowserType.valueOf(BROWSER)
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
    }

    fun getDriverInstance(): WebDriver {
        return this.driver
    }

    fun closeDriverInstance() {
        this.driver.quit()
    }

}
