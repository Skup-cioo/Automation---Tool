package common.ownResources;

import common.Common;
import lombok.Getter;

@Getter
public class Client extends Common {
    private final String clientEmail;
    private final String clientName;

    public Client(String name, String email) {
        if (Common.validText(email, Common.emailRegex) && Common.validText(name, "^[A-Z][a-z]{2,12}$")) {
            this.clientEmail = email;
            this.clientName = name;
        } else {
            throw new IllegalArgumentException("Email or Name is incorrect");
        }
    }
}
