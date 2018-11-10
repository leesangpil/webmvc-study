package my.study.entity.service;

import lombok.RequiredArgsConstructor;
import my.study.dto.member.MemberDTO;
import my.study.entity.Member;
import my.study.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDTO join(MemberDTO memberDTO) {
        Member save = memberRepository.save(new Member(memberDTO));
        return new MemberDTO(save);
    }
}
