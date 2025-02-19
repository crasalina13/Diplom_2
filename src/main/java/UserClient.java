import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {
    private static final String USER_PATH = "/api/auth";

    @Step
    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_PATH + "/register")
                .then();
    }

    @Step
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(USER_PATH + "/login")
                .then();
    }

    @Step
    public ValidatableResponse login(UserDataJson credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(USER_PATH + "/login")
                .then();
    }

    @Step
    public ValidatableResponse editData(UserDataJson userData, String bearerToken) {
        return given()
                .headers("Authorization", bearerToken)
                .spec(getBaseSpec())
                .body(userData)
                .when()
                .patch(USER_PATH + "/user")
                .then();
    }

    @Step
    public ValidatableResponse editData(UserDataJson userData) {
        return given()
                .spec(getBaseSpec())
                .body(userData)
                .when()
                .patch(USER_PATH + "/user")
                .then();
    }
}
