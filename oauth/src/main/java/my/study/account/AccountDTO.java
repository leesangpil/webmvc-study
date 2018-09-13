package my.study.account;

import lombok.Getter;
import lombok.Setter;
import my.study.account.enums.RoleEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class AccountDTO{

    @NotBlank
    @Length(min = 4, max = 12)
    private String username;

    @NotBlank
    @Length(min = 4, max = 12)
    private String password;

    @Range(min = 0, max = 200)
    private Integer age;

    protected List<RoleEnum> roles;
}
