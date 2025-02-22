import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.network.clients.UserClient;
import utils.UserGenerator;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;

@RunWith(Parameterized.class)
public class CreateUserParameterizedTest {
    private final User user;
    private final int statusCode;
    private final String message;
    private UserClient userClient;

    public CreateUserParameterizedTest(User user, int statusCode, String message) {
        this.user = user;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Parameterized.Parameters(name = "StatusCode: {1}, Message: {2}")
    public static Object[][] getUserInfo() {
        return new Object[][]{
                {UserGenerator.getRandom(), SC_OK, null},
                {UserGenerator.getWithEmailAndNameOnly(), SC_FORBIDDEN, "Email, password and name are required fields"},
                {UserGenerator.getWithEmailAndPasswordOnly(), SC_FORBIDDEN, "Email, password and name are required fields"},
                {UserGenerator.getWithNameAndPasswordOnly(), SC_FORBIDDEN, "Email, password and name are required fields"}
        };
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
    }

    @Test
    public void userCreatedAllFieldsAndWithoutPasswordAndWithoutNameAndWithoutEmailTest() {
        ValidatableResponse response = userClient.create(user);
        Assert.assertEquals(statusCode, response.extract().statusCode());
        Assert.assertEquals(message, response.extract().path("message"));
    }
}
