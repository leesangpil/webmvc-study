package my.study.dto.member;

import lombok.Data;
import my.study.entity.Member;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
@Data
public class MemberDTO {
    private Long id;

    private String name;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();
    }

    public MemberDTO(String name) {
        this.name = name;
    }
}
