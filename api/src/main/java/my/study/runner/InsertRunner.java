package my.study.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
@Component
//@Transactional
@Slf4j
public class JpaRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("saved : {}");
    }
    // detached 상태. (transaction이 끝나서 session밖으로 나왔을때.)
    // 이 트랜잭션이 끝나게 되면 commit을 해야 한다.(예외를 던져서 롤백을 하지 않는 이상.)
    // 이때, db에 저장하게 된다. (delete도 마찬가지)
    // 그리고 hibernate, jpa는 더 이상 이 객체에 대한 상태를 감지 할 수 없다.
    // (ex -> return 했을때)
    // 만약 이 상태에서 hibernate를 사용을 하고 싶다면, re-attach해줘야 한다.
    // (지원하는 메소드 : update, merge, saveOrUpdate)

    // 그러면 엔티티의 상태를 전이 시키고 싶다면?
    // 그 때, cascade를 사용하면 된다.
    // 엔티티의 관계가 parent-child 관계일 때, (member와 study는 parent와 child관걔가 아님. 서로가 독립적인 관계.)
    // 만약 위에서 member가 삭제되었을 때, study가 삭제되어야 한다면 parent-child 관계가 성립.
    //
}
