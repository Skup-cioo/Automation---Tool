package common;

import io.codearte.jfairy.Fairy;

import java.util.Locale;

public class GeneratorPolishName {
    private static final Fairy PLFAIRY = Fairy.create(Locale.forLanguageTag("pl"));

    public static String getRandomName() {
        return PLFAIRY.person().getFirstName();
    }

    public static String getRandomLastName() {
        return PLFAIRY.person().getLastName();
    }
}
