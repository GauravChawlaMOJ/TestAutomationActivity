package tests;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private LoginServiceTest loginService;
    public static final String USER_NAME = "username";
    private static final String CORRECT_PASSWORD = "drowssap";
    private static final String WRONG_PASSWORD = "password";
    @BeforeEach
    public void setUp() {
        loginService = new LoginServiceTest();
    }

    @Test
    public void testValidLogin() {
        boolean result = loginService.login(USER_NAME, CORRECT_PASSWORD);

        assertTrue(result);
    }

    @Test
    public void testInvalidLogin() {
        boolean result = loginService.login(USER_NAME, WRONG_PASSWORD);
        assertFalse(result);
    }

    @Test
    public void testEmptyUsernameLogin() {
        boolean result = loginService.login("", CORRECT_PASSWORD);

        assertFalse(result);
    }

    @Test
    public void testEmptyPasswordLogin() {
        boolean result = loginService.login(USER_NAME, "");
        assertFalse(result);
    }

    @Test
    public void testEmptyCredentialsLogin() {
        boolean result = loginService.login("", "");
        assertFalse(result);
    }

    @Test
    public void testPasswordEncoding() {
        String hashedPassword = loginService.encodePassword(CORRECT_PASSWORD);
        assertEquals(WRONG_PASSWORD, hashedPassword);
    }

    @Test
    public void testAccountLockout() {
        for (int i = 0; i < LoginServiceTest.MAX_LOGIN_ATTEMPTS; i++) {
            boolean result = loginService.login(USER_NAME, WRONG_PASSWORD);
            assertFalse(result);
        }
        boolean result = loginService.login(USER_NAME, WRONG_PASSWORD);
        assertFalse(result);  // Account should be locked out
    }

    @Test
    public void testDisabledAccountLogin() {
        loginService.disabledAccounts = new ArrayList<>();
        loginService.disabledAccounts.add(USER_NAME);
        boolean result = loginService.login(USER_NAME, CORRECT_PASSWORD);
        assertFalse(result);  // Account is inactive
        loginService.disabledAccounts = null;
    }

    @Test
    public void testInactiveAccountLogin() {
        loginService.inActiveAccounts = new ArrayList<>();
        loginService.inActiveAccounts.add(USER_NAME);
        boolean result = loginService.login(USER_NAME, CORRECT_PASSWORD);
        assertFalse(result);  // Account is inactive
        loginService.inActiveAccounts = null;
    }

    @Test
    public void testSessionManagement() {
        boolean loginResult = loginService.login(USER_NAME, CORRECT_PASSWORD);
        assertTrue(loginResult);

        boolean accessResult = loginService.accessProtectedResource();
        assertTrue(accessResult);
    }
}
