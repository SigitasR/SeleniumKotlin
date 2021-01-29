package lt.swed.pages.calc

import me.sig.framework.browser.BrowserFactory
import me.sig.framework.pages.BasePage
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy


class SwedPage : BasePage() {

    @FindBy(css = "button[data-wt-label='Accept all cookies']")
    private lateinit var cookiesAgreementYesButton: WebElement

    @FindBy(css = "label[for='dependantsCheck']")
    private lateinit var dependantsCheck: WebElement

    @FindBy(css = "label[for='dependants2']")
    private lateinit var dependantsTwoOrMore: WebElement

    @FindBy(id = "income")
    private lateinit var incomeInput: WebElement

    @FindBy(css = "#slider1 > div.ui-slider__field > input")
    private lateinit var loanAmountInputField: WebElement

    @FindBy(css = "#slider2 > div.ui-slider__bar > button")
    private lateinit var loanTermDragHandle: WebElement

    @FindBy(css = "#slider2 > div.ui-slider__bar > div.ui-slider__track")
    private lateinit var loanTermSliderTrack: WebElement

    @FindBy(css = "#slider2 > input")
    private lateinit var loanSliderHiddenValue: WebElement


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
        dependantsCheck.waitAndClick()
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