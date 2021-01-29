package me.sig.framework.test

import me.sig.framework.browser.BrowserFactory
import me.sig.framework.browser.ScreenshotUtil
import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import java.time.LocalDateTime

open class BaseWebTest {

    @AfterMethod(alwaysRun = true)
    fun tearDown(result: ITestResult) {

        if (!result.isSuccess) {
            ScreenshotUtil.takeScreenshot(
                BrowserFactory.getDriverInstance(),
                LocalDateTime.now().toString() + result.name
            )
        }

        BrowserFactory.closeDriverInstance()
    }

}
