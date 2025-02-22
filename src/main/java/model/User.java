package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;

    public User setEmailAndNameOnly(String email, String name) {
        this.email = email;
        this.name = name;
        return this;
    }

    public User setEmailAndPasswordOnly(String email, String password) {
        this.email = email;
        this.password = password;
        return this;
    }

    public User setWithNameAndPasswordOnly(String name, String password) {
        this.name = name;
        this.password = password;
        return this;
    }
}
