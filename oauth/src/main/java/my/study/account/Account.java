package my.study.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
}
