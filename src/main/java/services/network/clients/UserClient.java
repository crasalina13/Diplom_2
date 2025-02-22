package services.network.clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;
import services.network.Endpoints;
import services.network.dto.UserDataJson;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {

    @Step("Create user")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(Endpoints.USER_REGISTER_PATH.getPath())
                .then();
    }

    @Step("Login")
    public <T> ValidatableResponse login(T credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(Endpoints.USER_LOGIN_PATH.getPath())
                .then();
    }

    @Step("Edit user data")
    public ValidatableResponse editData(UserDataJson userData, String... bearerToken) {
        var requestSpecification = given().spec(getBaseSpec()).body(userData);

        if (bearerToken.length > 0 && bearerToken[0] != null) {
            requestSpecification.headers("Authorization", bearerToken[0]);
        }

        return requestSpecification
                .when()
                .patch(Endpoints.USER_PATH.getPath())
                .then();
    }
}
