package test.testamazon.persistance.types.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static test.testamazon.persistance.types.security.PermissionType.*;


@Getter
@RequiredArgsConstructor
public enum RoleType {

    PERSONAL(Set.of(PERSONAL_READ, PERSONAL_UPDATE, PERSONAL_CREATE, PERSONAL_DELETE));


    private final Set<PermissionType> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}