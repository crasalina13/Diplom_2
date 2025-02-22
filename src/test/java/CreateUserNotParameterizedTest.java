import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.network.clients.UserClient;
import utils.UserGenerator;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;

public class CreateUserNotParameterizedTest {
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
    }

    @Test
    public void createUserWhoIsAlreadyRegisteredTest() {
        userClient.create(user);
        ValidatableResponse response = userClient.create(user);
        Assert.assertEquals(SC_FORBIDDEN, response.extract().statusCode());
        Assert.assertEquals("User already exists", response.extract().path("message"));
    }
}
