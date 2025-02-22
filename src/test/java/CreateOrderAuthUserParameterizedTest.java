import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.network.clients.OrderClient;
import services.network.clients.UserClient;
import services.network.dto.IngredientsDataJson;
import utils.UserGenerator;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;

@RunWith(Parameterized.class)
public class CreateOrderAuthUserParameterizedTest {
    private final IngredientsDataJson body;
    private final int code;
    private final String message;
    private final Boolean success;
    private OrderClient orderClient;
    private UserClient userClient;
    private String token;

    public CreateOrderAuthUserParameterizedTest(IngredientsDataJson body, int code, String message, Boolean success) {
        this.body = body;
        this.code = code;
        this.message = message;
        this.success = success;
    }

    @Parameterized.Parameters(name = "StatusCode: {1}, Message: {2}, IsSuccess: {3}")
    public static Object[][] getOrderInfo() {
        return new Object[][]{
                {new IngredientsDataJson("61c0c5a71d1f82001bdaaa6d"), SC_OK, null, true},
                {new IngredientsDataJson(null), SC_BAD_REQUEST, "Ingredient ids must be provided", false},
        };
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
        User user = UserGenerator.getRandom();
        orderClient = new OrderClient();
        token = userClient.create(user).extract().path("accessToken");
    }

    @After
    public void tearDown() {
        userClient.deleteUser(token);
    }

    @Test
    public void createOrderAuthUserWithAndWithoutIngridientsTest() {
        ValidatableResponse response = orderClient.create(body, token);
        Assert.assertEquals(response.extract().statusCode(), code);
        Assert.assertSame(response.extract().path("success"), success);
        Assert.assertEquals(message, response.extract().path("message"));
    }
}
