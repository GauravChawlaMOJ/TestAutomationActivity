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
7. Improve LoginServiceTest code to fix sonar issues. 
(declare lists protected and encodedPassword as stringbuilder as string concatenation will discard the old
string for garbage collection and create a new one. Performance wise, it is better to use string builder. Also, sonar was suggesting that!)
8. Would create db scripts to create db and save user-names and passwords in it for login service.
9. Would use driver.navigate().refresh() to refresh the page to get case list so that some concrete assertions can be done 
(to confirm that login was successful). The reason for this is that, demo doesn't load case list sometimes (or load slowly) 
and the test might fail if it won't see/find the expected element(s)




