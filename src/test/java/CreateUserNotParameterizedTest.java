import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateUserNotParameterizedTest {
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandom();
    }

    @Test
    public void createUserWhoIsAlreadyRegisteredTest() {
        userClient.create(user);
        ValidatableResponse response = userClient.create(user);
        Assert.assertEquals(403, response.extract().statusCode());
        Assert.assertEquals("User already exists", response.extract().path("message"));
    }
}
