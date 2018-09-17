package my.study.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.study.account.enums.RoleEnum;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.List;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
@Entity
@Getter
@Setter
@ToString
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private Integer age;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private List<RoleEnum> roles;
}
