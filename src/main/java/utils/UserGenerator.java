package utils;

import model.User;
import net.datafaker.Faker;

public class UserGenerator {

    private static final Faker faker = new Faker();

    public static User getRandom() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String name = faker.name().fullName();
        return new User(email, password, name);
    }

    public static User getWithEmailAndNameOnly() {
        return new User().setEmailAndNameOnly(faker.internet().emailAddress(), faker.name().fullName());
    }

    public static User getWithEmailAndPasswordOnly() {
        return new User().setEmailAndPasswordOnly(faker.internet().emailAddress(), faker.internet().password());
    }

    public static User getWithNameAndPasswordOnly() {
        return new User().setWithNameAndPasswordOnly(faker.name().fullName(), faker.internet().password());
    }
}
