package my.study.runner;

import lombok.extern.slf4j.Slf4j;
import my.study.account.Account;
import my.study.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
@Slf4j
@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        String username = "splee";
        String password = "1111";
        Account account = accountService.createAccount(username, password);
        log.info("create account : {}, password : {}", account.getUsername(), account.getPassword());
    }
}
