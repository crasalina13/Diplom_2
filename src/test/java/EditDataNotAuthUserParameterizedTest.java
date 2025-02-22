import io.restassured.response.ValidatableResponse;
import net.datafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.network.clients.UserClient;
import services.network.dto.UserDataJson;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

@RunWith(Parameterized.class)
public class EditDataNotAuthUserParameterizedTest {
    private final UserDataJson body;
    private UserClient userClient;

    public EditDataNotAuthUserParameterizedTest(String name, String email, String password) {
        this.body = new UserDataJson(name, email, password);
    }

    @Parameterized.Parameters(name = "Name: {0}, Password: {1}, Email: {2}")
    public static Object[][] getUserInfo() {
        Faker faker = new Faker();
        return new Object[][]{
                {faker.name().firstName(), null, null},
                {null, faker.internet().emailAddress(), null},
                {null, null, faker.internet().password()}
        };
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
    }

    @Test
    public void editNameAndEmailAndPasswordNotAuthUserParameterizedTest() {
        ValidatableResponse response = userClient.editData(body);
        Assert.assertEquals(SC_UNAUTHORIZED, response.extract().statusCode());
        Assert.assertEquals("You should be authorised", response.extract().path("message"));
        Assert.assertFalse(response.extract().path("success"));
    }
}
