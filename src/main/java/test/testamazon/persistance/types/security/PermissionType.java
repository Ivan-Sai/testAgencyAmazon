package test.testamazon.persistance.types.security;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionType {

    PERSONAL_READ("personal:read"),
    PERSONAL_CREATE("personal:create"),
    PERSONAL_UPDATE("personal:update"),
    PERSONAL_DELETE("personal:delete");

    private final String permission;
}