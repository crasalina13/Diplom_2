import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginUserTest {
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandom();
    }

    @Test
    public void loginExistUserTest() {
        userClient.create(user);
        ValidatableResponse response = userClient.login(UserCredentials.from(user));
        Assert.assertEquals(200, response.extract().statusCode());
        Assert.assertNull(response.extract().path("message"));
    }

    @Test
    public void loginNotExistUserTest() {
        ValidatableResponse response = userClient.login(UserCredentials.from(user));
        Assert.assertEquals(401, response.extract().statusCode());
        Assert.assertEquals("email or password are incorrect", response.extract().path("message"));
    }
}
