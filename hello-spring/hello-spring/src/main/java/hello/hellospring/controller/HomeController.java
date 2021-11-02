package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//간단한 회원정보 제공.
//회원 관리용 홈
@Controller
public class HomeController {
    @GetMapping("/")
    public String hoem(){
        return "home";
    }
}
