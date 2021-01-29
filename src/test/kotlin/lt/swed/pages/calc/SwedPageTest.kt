package lt.swed.pages.calc

import me.sig.framework.test.BaseWebTest
import org.testng.annotations.Test

class SwedPageTest : BaseWebTest() {

    lateinit var swedPage: SwedPage

    @Test
    fun myTest() {
        swedPage = SwedPage(getWebDriver())

        swedPage.openBaseUrl()

        swedPage
            .clickCookiesAgreementYes()
            .clickDependantsCheckBox()
            .clickTwoOrMoreDependantsRadioButton()
            .enterIncome(1000)
            .enterLoanAmount(45000)
            .dragLoanTermHandleToNumberOfMonths(132)
    }

}