package common.ownResources;

import common.Common;
import lombok.Getter;

@Getter
public class Account extends Common {
    private final String email;
    private final String password;

    public Account(String email, String password) {
        if (Common.validText(email, Common.emailRegex) && Common.validText(password, "^.{6,}$")) {
            this.email = email;
            this.password = password;
        } else {
            throw new IllegalArgumentException("Email or Name is incorrect");
        }
    }
}
