package my.study.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
@Entity
@Getter
@Setter
@ToString
public class Account {

    @Id
    @NotBlank
    @Length(min = 4, max = 12)
    private String username;

    @NotBlank
    private String password;

    private Integer age;

    @OneToMany(mappedBy="username", fetch = FetchType.EAGER)
    private List<AccountRole> roles;
}
