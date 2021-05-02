package me.sig.framework.settings

val URL = System.getProperty("url", "https://www.swedbank.lt/private/credit/loans/home?language=ENG")
val BROWSER = System.getProperty("browser", "Chrome")
val EXPLICIT_WAIT_TIMEOUT = System.getProperty("explicitWaitTimeout", "10").toLong()
