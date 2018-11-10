package my.study.runner;

import lombok.RequiredArgsConstructor;
import my.study.dto.member.MemberDTO;
import my.study.entity.service.MemberService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
@Order(0)
@Component
@RequiredArgsConstructor
public class MemberInsertRunner implements ApplicationRunner {

    private final MemberService memberService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("0. member join");

        MemberDTO dto = memberService.join(makeMemberDTO());

        System.out.println("member join complete!!");
        System.out.println("id : " + dto.getId() + ", name : " + dto.getName());
    }

    private MemberDTO makeMemberDTO() {
        String name = "leesangpil";
        return new MemberDTO(name);
    }
}
