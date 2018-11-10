package my.study.repository;

import my.study.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by leesangpil on 2018. 11. 8..
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
