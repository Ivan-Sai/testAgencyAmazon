package test.testamazon.persistance.entities.token;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import test.testamazon.persistance.entities.user.UserEntity;
import test.testamazon.persistance.types.security.TokenType;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "tokens")
public class Token {

    @Id
    private String id;

    private String token;

    private TokenType tokenType;

    private Boolean expired;

    private Boolean revoked;

    private UserEntity user;

    public Token() {
        this.tokenType = TokenType.BEARER;
    }
}