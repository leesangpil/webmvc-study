package my.study.account;

import lombok.Getter;
import lombok.Setter;
import my.study.account.enums.GenderEnum;
import my.study.account.enums.RoleEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
public class AccountDTO{
    @NotBlank @Length(min = 4, max = 12)
    private String username;
    @NotBlank @Length(min = 4, max = 12)
    private String password;
    @Range(min = 0, max = 200)
    private Integer age;
    protected List<RoleEnum> roles;
    @NotBlank @Email
    private String email;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String homePhoneNumber;
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    private String cellPhoneNumber;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String emergencyNumber;
    @Nullable
    private String homeAddress;
    @Nullable
    private String companyAddress;
    @NotNull
    private GenderEnum gender;
}
