import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderTest {
    private OrderClient orderClient;
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandom();
        orderClient = new OrderClient();
    }

    @Test
    public void createOrderAuthUserWithWrongHashIngridientTest() {
        String token = userClient.create(user).extract().path("accessToken");
        IngridientsDataJson body = new IngridientsDataJson("test");
        ValidatableResponse response = orderClient.create(body, token);
        Assert.assertEquals(500, response.extract().statusCode());
    }

    @Test
    public void createOrderNotAuthUserWithWrongHashIngridientTest() {
        IngridientsDataJson body = new IngridientsDataJson("test");
        ValidatableResponse response = orderClient.create(body);
        Assert.assertEquals(500, response.extract().statusCode());
    }
}
