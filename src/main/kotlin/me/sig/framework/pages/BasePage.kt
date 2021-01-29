package me.sig.framework.pages

import me.sig.framework.browser.BrowserFactory
import me.sig.framework.settings.URL
import org.openqa.selenium.WebDriver

open class BasePage() : BaseWebOperations() {
    fun openUrl(url: String) {
        BrowserFactory.getDriverInstance().get(url)
    }

    fun openBaseUrl() {
        openUrl(URL)
    }

}