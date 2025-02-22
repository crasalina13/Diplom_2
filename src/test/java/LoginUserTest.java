import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCredentials;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.network.clients.UserClient;
import utils.UserGenerator;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class LoginUserTest {
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
    }

    @Test
    public void loginExistUserTest() {
        userClient.create(user);
        ValidatableResponse response = userClient.login(UserCredentials.from(user));
        Assert.assertEquals(SC_OK, response.extract().statusCode());
        Assert.assertNull(response.extract().path("message"));
    }

    @Test
    public void invalidEmailUserTest() {
        ValidatableResponse response = userClient.login(UserCredentials.from(user));
        Assert.assertEquals(SC_UNAUTHORIZED, response.extract().statusCode());
        Assert.assertEquals("email or password are incorrect", response.extract().path("message"));
    }

    @Test
    public void invalidPasswordUserTest() {
        userClient.create(user);
        user.setPassword("");
        ValidatableResponse response = userClient.login(UserCredentials.from(user));
        Assert.assertEquals(SC_UNAUTHORIZED, response.extract().statusCode());
        Assert.assertEquals("email or password are incorrect", response.extract().path("message"));
    }
}
