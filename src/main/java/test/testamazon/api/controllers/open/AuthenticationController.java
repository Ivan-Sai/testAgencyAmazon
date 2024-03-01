package test.testamazon.api.controllers.open;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.testamazon.api.data.AuthenticationData;
import test.testamazon.api.data.request.LoginData;
import test.testamazon.api.data.request.RegisterData;
import test.testamazon.services.security.AuthenticationService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/open/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationData> register(@RequestBody RegisterData data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationService.register(data));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationData> login(@RequestBody LoginData data) {
        return ResponseEntity.ok((authenticationService.login(data)));
    }
}