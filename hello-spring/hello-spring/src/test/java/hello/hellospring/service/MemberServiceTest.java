package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    /*아래 : 원 코드 : MemberService를 생성, 그 안에도 MemberRepository(정확히는 MemoryMemberRepository)를 가지고 있고,
    밑에 또 MemberRepository(MemoryMmeberRepository)를 생성함.
    하지만, MemoryMemberRepository는 임시 DB의 역할을 하는 친구여서 한번만 객체 생성이 되어야하는데, 지금은 그러지 못함.
    물론 MemoryMemberRepository의 칼럼역할을 하는 변수들(id, name)이 모두 static처리 되어있어서, 문제가 발생하지는 않지만, 이 예제가 아닌 다른 예제를 할 경우
    문제를 일으킬 수 있게 된다. -> DI를 통해서 이를 해결해준다. 즉, 한번만 이 MemoryMemberRepository의 객체를 생성하겠끔..

    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
     */

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);//다형성 : MemoryMemberRepository클래스의 객체를 memberRepository에 넣음(인터페이스-구현체 관계 -> 다형성 가능)
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { //테스트코드는 이렇게 한글로 적어도 된다. //원래 : join이었다.
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    //^ : 예외로직(중복회원)을 확인할 수가 없다.
    //아래서 해주어보자.
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

//        try{
//            memberService.join(member2);
//            fail(); //예외가 뜨는지 확인하는 것이기에 fail이다.
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        //위처럼 이것 하나때문에 try-catch넣는 것이 보기 안좋다.

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// memberService.join(member2)할 때 IllegalStateException이 발생해야한다. -> 이것을 확인해라.
        //Exception.class : 음.. 공부가 필요하다..
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");




        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}