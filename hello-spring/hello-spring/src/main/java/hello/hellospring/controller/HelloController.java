package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //MVC의 컨트롤러
public class HelloController {
    @GetMapping("hello") // Get Post방식 할때의 Get
    public String hello(Model model){ //MVC의 모델
        model.addAttribute("data", "hello");
        return "hello"; //resources의 templates에 hello.html을 찾아서 랜더링해라.
        //viewResolver라고 한다.
        //viewResolver작동 방식 : 아래
        //"resources:templates/" + (ViewNmae, 여기서는 hello) + ".html"
    }

    @GetMapping("hello-mvc") // MVC
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){ //ctrl + p : 파라미터 보여준다.
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string") // API - 개념맛보기
    @ResponseBody // http의 body에 값을 직접 넣어주겠다는 의미.
    //return되는 값을 http body에 직접 넣어준다 -> 이전에는 http body에 html head, html body, html 태그 등등의 html이 들어갔으나, 이를 싹 다 없에고 리턴되는 것을 다이렉트로 넣어준다.
    public String helloString(@RequestParam("name") String name){ //MVC를 사용하지 않기에 Model 파라미터 없다.
        return "hello " + name; //ctrl shift enter : 나머지 자동완성
    }

    @GetMapping("hello-api") // API
    @ResponseBody //객체를 return할 때 자동으로 객체변수를 JSON형태로 변환하여 http body에 넣어준다.
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        //이와 같이 getter, setter가 있는 것을, 자바빈 규약이라고 한다.
//        https://vividswan.github.io/2020/10/08/Java-%EC%9E%90%EB%B0%94%EB%B9%88-%EA%B7%9C%EC%95%BD.html

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
