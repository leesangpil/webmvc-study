package my.study.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum{
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    private String authority;
}
