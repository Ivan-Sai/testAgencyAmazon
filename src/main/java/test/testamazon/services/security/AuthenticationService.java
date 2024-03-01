package test.testamazon.services.security;


import test.testamazon.api.data.AuthenticationData;
import test.testamazon.api.data.request.LoginData;
import test.testamazon.api.data.request.RegisterData;

public interface AuthenticationService {
    AuthenticationData register(RegisterData data);

    AuthenticationData login(LoginData data);
}