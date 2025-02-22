package services.network;

import lombok.Getter;

@Getter
public enum Endpoints {
    BASE_URL("https://stellarburgers.nomoreparties.site/"),
    ORDER_PATH("api/orders"),
    USER_PATH("/api/auth/user"),
    USER_LOGIN_PATH("/api/auth/login"),
    USER_REGISTER_PATH("/api/auth/register");

    private final String path;

    Endpoints(String path) {
        this.path = path;
    }
}
