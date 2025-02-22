import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.network.clients.OrderClient;
import services.network.clients.UserClient;
import services.network.dto.IngredientsDataJson;
import utils.UserGenerator;

import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;

public class CreateOrderTest {
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
    public void createOrderAuthUserWithWrongHashIngridientTest() {
        String token = userClient.create(user).extract().path("accessToken");
        IngredientsDataJson body = new IngredientsDataJson("test");
        ValidatableResponse response = orderClient.create(body, token);
        Assert.assertEquals(SC_INTERNAL_SERVER_ERROR, response.extract().statusCode());
        userClient.deleteUser(token);
    }

    @Test
    public void createOrderNotAuthUserWithWrongHashIngridientTest() {
        IngredientsDataJson body = new IngredientsDataJson("test");
        ValidatableResponse response = orderClient.create(body);
        Assert.assertEquals(SC_INTERNAL_SERVER_ERROR, response.extract().statusCode());
    }
}
