package me.sig.framework.test

import me.sig.framework.browser.BrowserFactory
import me.sig.framework.browser.BrowserType
import me.sig.framework.settings.BROWSER
import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

open class BaseWebTest {
    protected lateinit var browserFactory: BrowserFactory
    protected lateinit var driver: WebDriver

    @BeforeMethod(alwaysRun = true)
    fun before() {
        this.browserFactory = BrowserFactory().createDriver(BrowserType.valueOf(BROWSER))
        this.driver = browserFactory.getDriverInstance()
    }

    @AfterMethod(alwaysRun = true)
    fun tearDown() {
        this.driver.quit()
    }

    fun getWebDriver(): WebDriver {
        return this.driver
    }

}