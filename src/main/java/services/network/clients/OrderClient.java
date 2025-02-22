package services.network.clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import services.network.Endpoints;
import services.network.dto.IngredientsDataJson;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {

    @Step("Create order")
    public ValidatableResponse create(IngredientsDataJson ingredients, String bearerToken) {
        var requestSpecification = given().spec(getBaseSpec()).body(ingredients);

        if (bearerToken != null) {
            requestSpecification.headers("Authorization", bearerToken);
        }

        return requestSpecification
                .when()
                .post(Endpoints.ORDER_PATH.getPath())
                .then();
    }

    @Step("Create order")
    public ValidatableResponse create(IngredientsDataJson ingredients) {
        return create(ingredients, null);
    }

    @Step("Get order")
    public ValidatableResponse get(String bearerToken) {
        var requestSpecification = given().spec(getBaseSpec());

        if (bearerToken != null) {
            requestSpecification.headers("Authorization", bearerToken);
        }

        return requestSpecification
                .when()
                .get(Endpoints.ORDER_PATH.getPath())
                .then();

    }

    @Step("Get order")
    public ValidatableResponse get() {
        return get(null);
    }
}
