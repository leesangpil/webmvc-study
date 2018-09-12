package my.study.controller;

import my.study.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JoinController {

    @Autowired
    AccountService accountService;

    @PostMapping("/join")
    public String join(@RequestParam String username, @RequestParam String password){
        accountService.createAccount(username, password);
        return "index";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }
}
