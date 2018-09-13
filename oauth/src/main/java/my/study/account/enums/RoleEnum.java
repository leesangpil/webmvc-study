package my.study.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum RoleEnum implements GrantedAuthority {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    private String authority;
}
