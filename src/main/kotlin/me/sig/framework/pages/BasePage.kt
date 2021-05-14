package me.sig.framework.pages

import me.sig.framework.settings.URL

open class BasePage : BaseWebOperations() {

    fun openUrl(url: String) {
        driver.get(url)
    }

    fun openBaseUrl() {
        openUrl(URL)
    }

}
