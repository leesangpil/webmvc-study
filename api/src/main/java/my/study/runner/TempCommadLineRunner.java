package my.study.runner;

import my.study.domain.Member;
import my.study.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by leesangpil on 2018. 9. 11..
 */
@Component
public class TempCommadLineRunner {

    // 임시
    @Bean
    public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
        return args -> {
            Member member = new Member();
            member.setUsername("splee");
            member.setName("leesangpil");
            memberRepository.save(member);
        };
    }
}
