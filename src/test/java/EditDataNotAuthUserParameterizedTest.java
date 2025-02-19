import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class EditDataNotAuthUserParameterizedTest {
    private final UserDataJson body;
    private UserClient userClient;

    public EditDataNotAuthUserParameterizedTest(UserDataJson body) {
        this.body = body;
    }

    @Parameterized.Parameters
    public static Object[][] getUserInfo() {
        return new Object[][]{
                {new UserDataJson(RandomStringUtils.randomAlphabetic(10), null, null)},
                {new UserDataJson(null, (RandomStringUtils.randomAlphabetic(10) + "@test.ru").toLowerCase(), null)},
                {new UserDataJson(null, null, RandomStringUtils.randomAlphabetic(10))}
        };
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
    }

    @Test
    public void editNameAndEmailAndPasswordNotAuthUserParameterizedTest() {
        ValidatableResponse response = userClient.editData(body);
        Assert.assertEquals("You should be authorised", response.extract().path("message"));
        Assert.assertEquals(401, response.extract().statusCode());
        Assert.assertFalse(response.extract().path("success"));
    }
}
