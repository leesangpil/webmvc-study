package my.study.controller;

import lombok.RequiredArgsConstructor;
import my.study.account.AccountDTO;
import my.study.account.AccountService;
import my.study.account.enums.RoleEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class JoinController {
    private final AccountService accountService;

    @PostMapping("/join")
    public String join(@Valid @RequestBody AccountDTO accountDTO){
        accountService.createAccount(accountDTO.getUsername(), accountDTO.getUsername(), RoleEnum.USER);
        return "index";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }
}
