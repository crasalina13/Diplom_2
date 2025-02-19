import org.apache.commons.lang3.RandomStringUtils;

public class User {
    public String email;
    public String password;
    public String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User() {
    }

    public static User getRandom() {
        final String email = RandomStringUtils.randomAlphabetic(10) + "@test.ru";
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String name = RandomStringUtils.randomAlphabetic(10);
        return new User(email, password, name);
    }

    public User setEmailAndNameOnly(String email, String name) {
        this.email = email;
        this.name = name;
        return this;
    }

    public User setEmailAndPasswordOnly(String email, String password) {
        this.email = email;
        this.password = password;
        return this;
    }

    public User setWithNameAndPasswordOnly(String name, String password) {
        this.name = name;
        this.password = password;
        return this;
    }

    public static User getWithEmailAndNameOnly() {
        return new User().setEmailAndNameOnly(RandomStringUtils.randomAlphabetic(10) + "@test.ru", RandomStringUtils.randomAlphabetic(10));
    }

    public static User getWithEmailAndPasswordOnly() {
        return new User().setEmailAndPasswordOnly(RandomStringUtils.randomAlphabetic(10) + "@test.ru", RandomStringUtils.randomAlphabetic(10));
    }

    public static User getWithNameAndPasswordOnly() {
        return new User().setWithNameAndPasswordOnly(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    }
}
