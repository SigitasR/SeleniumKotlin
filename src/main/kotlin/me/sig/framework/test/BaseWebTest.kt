package me.sig.framework.test

import me.sig.framework.browser.BrowserFactory
import org.testng.annotations.AfterMethod

open class BaseWebTest {

    @AfterMethod(alwaysRun = true)
    fun tearDown() {
        BrowserFactory.closeDriverInstance()
    }

}