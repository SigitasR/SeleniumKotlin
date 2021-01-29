package me.sig.framework.pages

import me.sig.framework.settings.EXPLICIT_WAIT_TIMEOUT
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

open class BaseWebOperations(val driver: WebDriver) {
    init {
        PageFactory.initElements(driver, this)
    }

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

    fun waitForJavaScript() {
        val wait = createWait()
        val js = driver as JavascriptExecutor
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
        val js = driver as JavascriptExecutor
        js.executeScript(
            "arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });",
            element
        )
    }

    private fun createWait() = WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT)

}

