package my.study.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
@Component
@Slf4j
public class JpaRunner2 implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("saved : {}");
    }
}
