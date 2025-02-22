package services.network.clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import services.network.Endpoints;

public class RestAssuredClient {
    public RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(Endpoints.BASE_URL.getPath())
                .build();
    }
}
