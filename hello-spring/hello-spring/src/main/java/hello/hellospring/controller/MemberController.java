package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//@Controller
//public class MemberController {
//
//}
//위와 같은 기본코드만 실행시 스프링 컨테이너가 생긴다.
//이 컨테이너에 @Controller가 있는 클래스의 객체를 생성해서 넣어둔다.
//그리고 관리한다.
//이것을 스프링컨테이너에서 스프링빈이 관리된다고 표현한다.

@Controller
//@Controller는 @Component를 포함한다. -> 컴포넌트 스캔이다.
public class MemberController {

//    private final MemberService memberService = new MemberService();
    // 다른 컨트롤러객체들도 다 만들어져서 컨테이너 안에 들어있게 된다.
    // 그리고 위 코드를 사용하면 MemberService는 컨트롤러 개수만큼 생성된다.
    // 하지만, 그럴 필요가 없다. MemberService는 한번만 생성되는 것이 효율적이다
    // -> 따라서 이 MemberService를 컨테이너에 직접 만들고, 그것을 받아쓰면 된다.
    // 이를

    //@Autowired private MemberService memberService; //이런 DI방식을 필드 주입이라고 부른다

    private final MemberService memberService;

    @Autowired//이 Annotation이 붙어있으면, 컨테이너에서 이 컨트롤러를 생성할 때, 컨테이너 안에 있는 MemberService memberService를 자동으로 넣어준다.
    //단, MemberService는 컨테이너에 들어있지 않은 상황이다. -> 해당 클래스(MemberService)선언시 @Service 어노테이션 붙이자 -> 컨테이너에 등록해준다.
    //이렇게 밖에서(컨테이너) 넣어주는 이것을 DI(Dependency Injection)이라고 한다.
    //이런 DI방식을 생성자 주입이라고 한다.(생성자에 인자로 넣어준다는 의미로 이해하자.)
    public MemberController(MemberService memberService) { //constructor이다. 객체 생성시 parameter로 들어가는 것을 정해주는..
        this.memberService = memberService;
    }

//    private MemberService memberService; // 이런 DI방식을 setter 주입이라고 한다.
    //문제점 : setter가 public으로 열려있다. -> 중간에 의도치 않게 바뀔 수 있다. 의존관계가 중간에 바뀌는 경우는 없다.
//
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    //가장 좋은 DI방식은 생성자 주입.

    @GetMapping("/members/new") // 같은 주소를 매핑하더라도, Get방식으로 데이터가 들어오면 이 컨트롤러를 실행
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // 같은 주소를 매핑하더라도, POST방식으로 데이터가 들어오면 이 컨트롤러를 실행
    public String create(MemberForm form){
        //여기 인자로 들어있는 인자가 중요하다. : form의 name에 해당하는 클래스 변수에 해당 값을 넣어준다.(setName을 통해서.. -> setName 없으면 에러난다.)
        //여기 인자에 들어가있는 곳으로 form이 전달되어, 인자의 setter를 통해 form의 값들이 저장된다.
        //중요하다.
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/"; // redirect to home(/)
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}