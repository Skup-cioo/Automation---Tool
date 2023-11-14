package common;

import lombok.Getter;

@Getter
public class User {
    private final String name;
    private final String job;

    public User(String name, String job) {
        if (Common.validText(name, "^.{3,}$") || Common.validText(job, "^.{3,}$")) {
            this.name = name;
            this.job = job;
        } else {
            throw new IllegalArgumentException("Name or Job is incorrect");
        }
    }
}
