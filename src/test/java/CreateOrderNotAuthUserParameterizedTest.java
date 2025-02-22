import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.network.clients.OrderClient;
import services.network.dto.IngredientsDataJson;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;

@RunWith(Parameterized.class)
public class CreateOrderNotAuthUserParameterizedTest {
    private final IngredientsDataJson body;
    private final int code;
    private final String message;
    private final Boolean success;
    private OrderClient orderClient;

    public CreateOrderNotAuthUserParameterizedTest(IngredientsDataJson body, int code, String message, Boolean success) {
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
        orderClient = new OrderClient();
    }

    @Test
    public void createOrderNotAuthUserWithAndWithoutIngridientsTest() {
        ValidatableResponse response = orderClient.create(body);
        Assert.assertEquals(response.extract().statusCode(), code);
        Assert.assertSame(response.extract().path("success"), success);
        Assert.assertEquals(message, response.extract().path("message"));
    }
}
