package tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class SeleniumTests {
    private WebDriver driver;
    private static final String ERROR_CLASS_NAME = "error-summary";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "employment_service@mailinator.com";
    private static final String PASSWORD_VALUE = "Nagoya0102";
    private static final String WRONG_PASSWORD_VALUE = "wrong_password";
    private static final String INVALID_EMAIL = "invalid_email";
    private static final String EMAIL_BLANK_ERROR_MESSAGE = "Email address cannot be blank";
    private static final String PASSWORD_BLANK_ERROR_MESSAGE = "Password cannot be blank";
    private static final String INVALID_EMAIL_ERROR_MESSAGE = "Email address is not valid";
    private static final String SAVE_BUTTON = "save";

    @BeforeEach
    public void setUp() throws InterruptedException {
        // Set up WebDriver before each test
        String s = System.getProperty("user.dir");
        s = s + "/resources/chromedriver";
        System.setProperty("webdriver.chrome.driver", s);
        log.info("Chrome driver path is set");
        driver = new ChromeDriver();
        driver.get("https://manage-case.perftest.platform.hmcts.net/");
        Thread.sleep(1000);
    }

    @AfterEach
    public void tearDown() {
        // Clean up WebDriver after each test
        log.info("Quitting driver..");
        driver.quit();
    }

    @Test
    public void testValidLogin() {
        driver.findElement(By.id(USER_NAME)).sendKeys(EMAIL);
        driver.findElement(By.id(PASSWORD)).sendKeys(PASSWORD_VALUE);
        driver.findElement(By.name(SAVE_BUTTON)).click();
        // Assert that the user is redirected to the expected page
        assertEquals("https://manage-case.perftest.platform.hmcts.net/", driver.getCurrentUrl());
        assertEquals("HMCTS Manage cases", driver.getTitle());
    }

    @Test
    public void testInvalidPassword() {
        driver.findElement(By.id(USER_NAME)).sendKeys(EMAIL);
        driver.findElement(By.id(PASSWORD)).sendKeys(WRONG_PASSWORD_VALUE);
        driver.findElement(By.name(SAVE_BUTTON)).click();
        assertTrue(driver.findElement(By.className(ERROR_CLASS_NAME)).getText().contains("Check your email address"));
        assertTrue(driver.findElement(By.className(ERROR_CLASS_NAME)).getText().contains("Check your password"));
    }

    @Test
    public void testInvalidUsername() {
        driver.findElement(By.id(USER_NAME)).sendKeys(INVALID_EMAIL);
        driver.findElement(By.id(PASSWORD)).sendKeys(PASSWORD_VALUE);
        driver.findElement(By.name(SAVE_BUTTON)).click();
        assertTrue(driver.findElement(By.className(ERROR_CLASS_NAME)).getText().contains(INVALID_EMAIL_ERROR_MESSAGE));
    }

    @Test
    public void testEmptyUsernameLogin() {
        driver.findElement(By.id(USER_NAME)).sendKeys("");
        driver.findElement(By.id(PASSWORD)).sendKeys(PASSWORD_VALUE);
        driver.findElement(By.name(SAVE_BUTTON)).click();
        assertTrue(driver.findElement(By.className(ERROR_CLASS_NAME)).getText().contains(EMAIL_BLANK_ERROR_MESSAGE));
    }

    @Test
    public void testEmptyPasswordLogin() {
        driver.findElement(By.id(USER_NAME)).sendKeys(EMAIL);
        driver.findElement(By.id(PASSWORD)).sendKeys("");
        driver.findElement(By.name(SAVE_BUTTON)).click();
        assertTrue(driver.findElement(By.className(ERROR_CLASS_NAME)).getText().contains(PASSWORD_BLANK_ERROR_MESSAGE));
    }

    @Test
    public void testEmptyCredentialsLogin() {
        driver.findElement(By.id(USER_NAME)).sendKeys("");
        driver.findElement(By.id(PASSWORD)).sendKeys("");
        driver.findElement(By.name(SAVE_BUTTON)).click();
        assertTrue(driver.findElement(By.className(ERROR_CLASS_NAME)).getText().contains(EMAIL_BLANK_ERROR_MESSAGE));
        assertTrue(driver.findElement(By.className(ERROR_CLASS_NAME)).getText().contains(PASSWORD_BLANK_ERROR_MESSAGE));
    }
}
