//통합테스트 : db까지 포함한.
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // db에는 transaction이라는 개념이 있다. insert query를 한 다음, commit을 해주어야 반영이 된다.
// 그리고 보통 auto commit 모드로 진행되어, 무조건 commit한다.
// insert query, selece query날리고도, rollback하면 데이터가 반영 안된다. _-> 이렇게 검증할 수 있는 방법이 있다.
//이렇게 test앞에 @Transactional어노테이션을 달면, 테스트를 돌리고 롤백해준다.
//테스트전에 transaction을 걸고 테스트하면, 테스트가 끝나고 롤백한다.
//DB롤백 공부하자.
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService; //실제 구현 코드에서는 constructor로 해주었지만, test에서는 가장 편한 방법으로 해주어도 된다.
    @Autowired MemberRepository memberRepository;

    //필요없어졌다. : Transactional덕분.
//    @AfterEach
//    public void afterEach(){
//        memberRepository.clearStore();
//    }

    @Test
    void 회원가입() { //테스트코드는 이렇게 한글로 적어도 된다. //원래 : join이었다.
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member); //insert query 날림.

        //then
        Member findMember = memberService.findOne(saveId).get(); // selece query날림
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
        //then
    }
}