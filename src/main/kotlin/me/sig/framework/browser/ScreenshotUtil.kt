package me.sig.framework.browser

import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import java.io.File

class ScreenshotUtil {
    companion object {
        fun takeScreenshot(driver: WebDriver, filePrefix: String) {
            val shot = driver as TakesScreenshot
            val screen = shot.getScreenshotAs(OutputType.FILE)
            FileUtils.copyFile(screen, File("build/screenshots/$filePrefix-screenshot.png"))
        }
    }
}
