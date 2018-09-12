package my.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
@Controller
public class HelloController {


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/my")
    public String my() {
        return "my";
    }

    @GetMapping("/admin")
    public String admin() {return "admin";}
}
