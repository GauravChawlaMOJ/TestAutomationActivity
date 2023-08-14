# TestAutomationActivity

The project contains two types of test files.
1. Selenium Tests
It is to test login functionality for the HMCTS demo site.
 - Test cases for scenarios like, empty credentials, wrong credentials etc. are covered by tests.
 - Chrome webdriver is used for testing. So to, run these tests, chrome version >= 115 has to be installed.
 - It takes time to load the idam login page, so I've added a sleep of 1 second so that the test can see elements after load and the test doesn't fail before even the page is loaded.
  
2. JUnit tests
   I have used a dummy login service and verified the credentials, whether the account is disabled, inactive etc. with the unit tests.
   
I would like to improve the tests by following:

1. Add cross browser tests. By setting browser parameter to the beforeTest for example, and pass different browser types (e.g. chrome, firefox, safari, edge etc.). Might need org.testng dependencies for this.

2. Improve logging, I have added logging to it but it seems like there's some dependency version mis-match. Would have loved to add more logging and make it work.

3. I would like to learn/implement tests in other frameworks too e.g. cypress and TestNG to get knowledge about them. Cypress is even useed in one of our frontend projects in ET but I haven't implemented it, QAs from an external feature team are responsible for them.

4. Increase my knowledge in selenium webdriver tests, make use of parameterised tests to increase testing scenarios.

5. For Junit tests, I would like to add more logging.
6. Also, won't write the password directly into code, would read from secrets, azure key-vaults. 

