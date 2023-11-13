package common;

import lombok.Getter;

@Getter
public class User {
    private String name;
    private String job;

    public void setName(String name) {
        if (name.length() > 3) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name is incorrect");
        }
    }

    public void setJob(String job) {
        if (job.length() > 3) {
            this.job = job;
        } else {
            throw new IllegalArgumentException("Job is incorrect");
        }
    }
}
