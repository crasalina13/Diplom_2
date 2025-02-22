import com.google.gson.Gson;
import io.restassured.response.ValidatableResponse;
import model.User;
import net.datafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.network.clients.UserClient;
import services.network.dto.UserDataJson;
import utils.UserGenerator;

import java.util.HashMap;

import static org.apache.http.HttpStatus.SC_OK;

public class EditDataAuthUserTest {
    private User user;
    private UserClient userClient;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
    }

    @Test
    public void editNameAuthUserTest() {
        String token = userClient.create(user).extract().path("accessToken");
        UserDataJson body = new UserDataJson(new Faker().name().firstName(), null, null);
        ValidatableResponse response = userClient.editData(body, token);
        HashMap<String, String> userInfo = response.extract().path("user");
        Assert.assertEquals(SC_OK, response.extract().statusCode());
        User expectedUser = new User(user.getEmail(), null, body.getName());
        Gson gson = new Gson();
        String actualBody = gson.toJson(userInfo);
        String expectedBody = gson.toJson(expectedUser);
        Assert.assertEquals(expectedBody, actualBody);
        Assert.assertTrue(response.extract().path("success"));
    }

    @Test
    public void editEmailAuthUserTest() {
        String token = userClient.create(user).extract().path("accessToken");
        UserDataJson body = new UserDataJson(null, new Faker().internet().emailAddress(), null);
        ValidatableResponse response = userClient.editData(body, token);
        HashMap<String, String> userInfo = response.extract().path("user");
        Assert.assertEquals(SC_OK, response.extract().statusCode());
        User expectedUser = new User(body.getEmail(), null, user.getName());
        Gson gson = new Gson();
        String actualBody = gson.toJson(userInfo);
        String expectedBody = gson.toJson(expectedUser);
        Assert.assertEquals(expectedBody, actualBody);
        Assert.assertTrue(response.extract().path("success"));
    }
}
