import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.network.clients.OrderClient;
import services.network.clients.UserClient;
import utils.UserGenerator;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class GetOrderTest {
    private OrderClient orderClient;
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        orderClient = new OrderClient();
    }

    @Test
    public void getOrderAuthUserTest() {
        String token = userClient.create(user).extract().path("accessToken");
        ValidatableResponse response = orderClient.get(token);
        Assert.assertEquals(SC_OK, response.extract().statusCode());
        Assert.assertTrue(response.extract().path("success"));
        userClient.deleteUser(token);
    }

    @Test
    public void getOrderNotAuthUserTest() {
        ValidatableResponse response = orderClient.get();
        Assert.assertEquals(SC_UNAUTHORIZED, response.extract().statusCode());
        Assert.assertEquals("You should be authorised", response.extract().path("message"));
    }
}
