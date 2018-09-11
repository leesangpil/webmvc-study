package my.study.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by leesangpil on 2018. 9. 11..
 */
@Entity
@Setter
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
}
