package me.sig.framework.test

import me.sig.framework.browser.BrowserFactory
import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

open class BaseWebTest {
    protected lateinit var driver: WebDriver

    @BeforeMethod(alwaysRun = true)
    fun before() {
        this.driver = BrowserFactory.getDriverInstance()
    }

    @AfterMethod(alwaysRun = true)
    fun tearDown() {
        BrowserFactory.closeDriverInstance()
    }

}