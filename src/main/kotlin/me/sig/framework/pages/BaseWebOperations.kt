package me.sig.framework.pages

import me.sig.framework.browser.BrowserFactory
import me.sig.framework.browser.isMacOs
import me.sig.framework.settings.EXPLICIT_WAIT_TIMEOUT
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

open class BaseWebOperations() {
    protected val driver = BrowserFactory.getDriverInstance()

    fun waitForElementToBeDisplayed(element: WebElement) {
        val wait = createWait()
        wait.until(ExpectedConditions.visibilityOf(element))
    }

    fun waitForElementToBeNotDisplayed(element: WebElement) {
        val wait = createWait()
        wait.until(ExpectedConditions.invisibilityOf(element))
    }

    fun waitForElementToBeEnabled(element: WebElement) {
        val wait = createWait()
        wait.until(ExpectedConditions.elementToBeClickable(element))
    }

    fun waitForElement(element: WebElement) {
        waitForElementToBeDisplayed(element)
        waitForElementToBeEnabled(element)
    }

    fun waitForJavaScript() {
        val wait = createWait()
        val js = BrowserFactory.getDriverInstance() as JavascriptExecutor
        wait.until(
            ExpectedCondition<Boolean> {
                js.executeScript("return document.readyState === 'complete'") == true
            }
        )

    }

    fun waitForElementValueAttributeToBeNotEmpty(element: WebElement) {
        val wait = createWait()
        wait.until(
            ExpectedCondition<Boolean> {
                element.getAttribute("value").isNotEmpty()
            }
        )
    }

    fun scrollToElement(element: WebElement) {
        val js = BrowserFactory.getDriverInstance() as JavascriptExecutor
        js.executeScript(
            "arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });",
            element
        )
    }

    fun WebElement.waitAndClick() {
        waitForJavaScript()
        waitForElement(this)
        this.click()
    }

    fun WebElement.clearAndType(text: String) {
        waitForJavaScript()
        waitForElement(this)

        if (isMacOs()) {
            this.sendKeys(
                Keys.chord(Keys.COMMAND, "a"),
                Keys.BACK_SPACE
            )
        } else {
            this.sendKeys(
                Keys.chord(Keys.CONTROL, "a"),
                Keys.BACK_SPACE
            )
        }

        this.sendKeys(text)
    }

    private fun createWait() = WebDriverWait(BrowserFactory.getDriverInstance(), EXPLICIT_WAIT_TIMEOUT)

}
