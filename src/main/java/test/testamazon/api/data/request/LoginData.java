package test.testamazon.api.data.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginData {
    private String username;
    private String password;
}