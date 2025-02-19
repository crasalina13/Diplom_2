import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetOrderTest {
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
    public void getOrderAuthUserTest() {
        String token = userClient.create(user).extract().path("accessToken");
        ValidatableResponse response = orderClient.get(token);
        Assert.assertEquals(200, response.extract().statusCode());
        Assert.assertTrue(response.extract().path("success"));
    }

    @Test
    public void getOrderNotAuthUserTest() {
        ValidatableResponse response = orderClient.get();
        Assert.assertEquals(401, response.extract().statusCode());
        Assert.assertEquals("You should be authorised", response.extract().path("message"));
    }
}
