package common;

import lombok.Getter;

@Getter

public class Account {
    private final String email;
    private final String password;

    public Account(String email, String password) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";
        if (Common.validText(email, regex) || Common.validText(password, "^.{6,}$")) {
            this.email = email;
            this.password = password;
        } else {
            throw new IllegalArgumentException("Email or Password is incorrect");
        }
    }
}
