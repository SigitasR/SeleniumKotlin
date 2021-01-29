package lt.swed.pages.calc

import me.sig.framework.test.BaseWebTest
import org.testng.annotations.Test

class SwedPageTest : BaseWebTest() {

    @Test
    fun loanCalculatorTest() {
        val swedPage = SwedPage()

        swedPage.openBaseUrl()

        swedPage
            .clickCookiesAgreementYes()
            .clickDependantsCheckBox()
            .clickTwoOrMoreDependantsRadioButton()
            .enterIncome(1000)
            .enterLoanAmount(45000)
            .dragLoanTermHandleToNumberOfMonths(132)
            .assertMaximumLoanAmountText("49739")
            .assertMonthlyPaymentText("386")
    }

}
