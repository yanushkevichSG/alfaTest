package utilities;

import com.github.javafaker.Faker;

public class Fake {
    private static final Faker FAKER = new Faker();

    private Fake() {}

    public static String login() { return FAKER.name().username(); }

    public static String password() {
        return FAKER.internet().password();
    }

    public static String notValidLoginDataRegex() { return FAKER.regexify("^[^A-Za-z.,/'_ -]+$"); }

    public static String generateNumber(int size) {
        return String.valueOf(FAKER.number().digits(size));
    }

    public static String generateLetters(int size) {
        return FAKER.lorem().characters(size, false, false);
    }
}
