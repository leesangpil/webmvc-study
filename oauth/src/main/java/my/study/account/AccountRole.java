package my.study.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.study.account.enums.RoleEnum;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
public class AccountRole implements GrantedAuthority {
    @Id
    @Column(nullable = false)
    private String username;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private RoleEnum roleEnum;

    @Override
    public String getAuthority() {
        return roleEnum.getAuthority();
    }
}
