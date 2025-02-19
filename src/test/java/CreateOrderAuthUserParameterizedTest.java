import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateOrderAuthUserParameterizedTest {
    private final IngridientsDataJson body;
    private final int code;
    private final String message;
    private final Boolean success;
    private OrderClient orderClient;
    private UserClient userClient;
    private User user;

    public CreateOrderAuthUserParameterizedTest(IngridientsDataJson body, int code, String message, Boolean success) {
        this.body = body;
        this.code = code;
        this.message = message;
        this.success = success;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderInfo() {
        return new Object[][]{
                {new IngridientsDataJson("61c0c5a71d1f82001bdaaa6d"), 200, null, true},
                {new IngridientsDataJson(null), 400, "Ingredient ids must be provided", false},
        };
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandom();
        orderClient = new OrderClient();
    }

    @Test
    public void createOrderAuthUserWithAndWithoutIngridientsTest() {
        String token = userClient.create(user).extract().path("accessToken");
        ValidatableResponse response = orderClient.create(body, token);
        Assert.assertEquals(response.extract().statusCode(), code);
        Assert.assertSame(response.extract().path("success"), success);
        Assert.assertEquals(message, response.extract().path("message"));
    }
}
