package me.sig.framework.pages

import me.sig.framework.settings.URL
import org.openqa.selenium.WebDriver

open class BasePage(driver: WebDriver) : BaseWebOperations(driver) {
    fun openUrl(url: String) {
        this.driver.get(url)
    }

    fun openBaseUrl() {
        openUrl(URL)
    }

}