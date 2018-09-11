package my.study.repository;

import my.study.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by leesangpil on 2018. 9. 11..
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
