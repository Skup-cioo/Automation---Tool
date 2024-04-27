package common;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum AppName {
    GOOGLE("GOOGLE"),
    MOUSE("MOUSE"),
    AUTOMATION("AUTOMATION"),
    O2("O2");

    private final String description;

    public static AppName getApplicationName(String applicationName) {
        return Arrays.stream(values()).filter((element) -> element.description.equalsIgnoreCase(applicationName)).findFirst().orElseThrow(() -> new RuntimeException("Application not recognized: " + applicationName));
    }
}
