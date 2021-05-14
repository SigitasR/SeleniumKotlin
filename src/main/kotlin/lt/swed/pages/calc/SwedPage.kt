package lt.swed.pages.calc

import assertk.assertThat
import assertk.assertions.isEqualTo
import me.sig.framework.browser.BrowserFactory
import me.sig.framework.pages.BasePage
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions


class SwedPage : BasePage() {

    private val cookiesAgreementYesButton get() = driver.findElement(By.cssSelector("button[data-wt-label='Accept all cookies']"))
    private val dependantsCheck get() = driver.findElement(By.cssSelector("label[for='dependantsCheck']"))
    private val dependantsTwoOrMore get() = driver.findElement(By.cssSelector("label[for='dependants2']"))
    private val incomeInput get() = driver.findElement(By.id("income"))
    private val loanAmountInputField get() = driver.findElement(By.cssSelector("#slider1 > div.ui-slider__field > input"))
    private val loanTermDragHandle get() = driver.findElement(By.cssSelector("#slider2 > div.ui-slider__bar > button"))
    private val loanTermSliderTrack get() = driver.findElement(By.cssSelector("#slider2 > div.ui-slider__bar > div.ui-slider__track"))
    private val loanSliderHiddenValue get() = driver.findElement(By.cssSelector("#slider2 > input"))
    private val monthlyPayment get() = driver.findElement(By.id("month-payment"))
    private val maxLoan get() = driver.findElement(By.id("slider-financed"))

    fun clickCookiesAgreementYes(): SwedPage {
        cookiesAgreementYesButton.waitAndClick()
        waitForElementToBeNotDisplayed(cookiesAgreementYesButton)
        return this
    }

    fun clickDependantsCheckBox(): SwedPage {
        dependantsCheck.waitAndClick()
        return this
    }

    fun clickTwoOrMoreDependantsRadioButton(): SwedPage {
        dependantsTwoOrMore.waitAndClick()
        return this
    }

    fun enterIncome(incomeAmount: Int): SwedPage {
        incomeInput.clearAndType(incomeAmount.toString())
        return this
    }

    fun enterLoanAmount(loanAmount: Int): SwedPage {
        loanAmountInputField.clearAndType(loanAmount.toString())
        return this
    }

    fun dragLoanTermHandleToNumberOfMonths(numberOfMonths: Int): SwedPage {
        scrollToElement(loanTermSliderTrack)
        waitForJavaScript()
        waitForElementToBeEnabled(loanTermSliderTrack)
        waitForElementToBeEnabled(loanTermDragHandle)
        dragSliderToStartingPosition(loanTermSliderTrack, loanTermDragHandle)
        dragUntilMonth(numberOfMonths)
        return this
    }

    fun assertMonthlyPaymentText(expectedMonthlyPayment: String): SwedPage {
        assertThat(monthlyPayment.text, name = "Monthly payment").isEqualTo(expectedMonthlyPayment)
        return this
    }

    fun assertMaximumLoanAmountText(expectedMaxLoan: String): SwedPage {
        assertThat(maxLoan.text, name = "Maximum loan amount").isEqualTo(expectedMaxLoan)
        return this
    }

    private fun dragSliderToStartingPosition(track: WebElement, handle: WebElement) {
        val sliderHandlePosition = handle.location.getX()
        val sliderWidth = track.size.getWidth()
        moveSlider(loanTermDragHandle, sliderWidth - sliderHandlePosition)
    }

    private fun moveSlider(element: WebElement, xOffset: Int) {
        val moveAction = Actions(BrowserFactory.getDriverInstance())
        moveAction.dragAndDropBy(element, xOffset, 0).release().build().perform()
    }

    private fun dragUntilMonth(numberOfMonths: Int) {
        waitForElementValueAttributeToBeNotEmpty(loanSliderHiddenValue)
        var hiddenMonthsValue = loanSliderHiddenValue.getAttribute("value").toInt()
        while (hiddenMonthsValue < numberOfMonths) {
            moveSlider(loanTermDragHandle, 6)
            waitForJavaScript()
            waitForElementValueAttributeToBeNotEmpty(loanSliderHiddenValue)
            hiddenMonthsValue = loanSliderHiddenValue.getAttribute("value").toInt()
        }
    }


}
