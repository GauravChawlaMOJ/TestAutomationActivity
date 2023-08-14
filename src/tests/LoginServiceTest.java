package tests;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static tests.MainTest.USER_NAME;

public class LoginServiceTest {
    public static final int MAX_LOGIN_ATTEMPTS = 3;
    private int loginAttempts = 0;
    private List<String> lockedAccounts;
    public List<String> inActiveAccounts;
    public List<String> disabledAccounts;
    private static final String PASSWORD = "password";

    private boolean isAccountDisabled(String username) {
        return disabledAccounts != null && disabledAccounts.contains(username);
    }

    private boolean isAccountLocked(String username) {
        // Logic to check if the account is locked
        return lockedAccounts != null && lockedAccounts.contains(username);
    }

    private boolean isAccountInactive(String username) {
        // Logic to check if the account is inactive
        return inActiveAccounts != null && inActiveAccounts.contains(username);
    }

    public String encodePassword(String password) {
        // Logic to encode/hash the password (in this example just reversing the order of password for testing)
        char ch;
        String encodedPassword = "";
        for (int i=password.length() - 1; i > -1 ; i--)
        {
            ch= password.charAt(i);
            encodedPassword= encodedPassword + ch;
        }
        return encodedPassword;
    }

    private String getPassword() {
        return PASSWORD;
    }

    public boolean login(String username, String password) {
        if (!Objects.equals(username, USER_NAME)) {
            return false;
        }
        if (isAccountDisabled(username)) {
            return false;
        }

        if (isAccountLocked(username)) {
            return false;
        }

        if (isAccountInactive(username)) {
            return false;
        }

        String storedPassword = getPassword();
        String encodedPassword = encodePassword(password);

        if (!Strings.isNullOrEmpty(encodedPassword)
                && encodedPassword.equals(storedPassword)) {
            loginAttempts = 0; // Reset login attempts on successful login
            if (lockedAccounts != null) {
                lockedAccounts.remove(username);
            }
            return true;
        } else {
            loginAttempts++;
            if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                if (lockedAccounts == null) {
                    lockedAccounts = new ArrayList<>();
                }
                lockedAccounts.add(username);
            }
            return false;
        }
    }

    public boolean accessProtectedResource() {
        // Logic to access a protected resource after successful login
        return true;
    }
}
