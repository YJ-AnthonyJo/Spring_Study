package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional //jpa로 할때 service에 항상 필요하다.
//@Service //실행시 스프링 컨테이너에 자동 등록해줌. //자바코드로 직접 빈 등록위해 주석처리함
//사실 이 Servic는 @Component의 특수한 케이스이다.
//즉, @Service가 @Component를 가지고 있다. -> 컴포넌트 스캔이다.
public class MemberService {
//ctrl shift t : 테스트 자동 생성..와우..

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //final : 해당 변수 변경 불가.
    private final MemberRepository memberRepository; //memberRepository는 한번만 선언되어 여러메소드에서 지속적으로 같은 것이 사용되어야한다.

//    @Autowired//자바코드로 직접 빈 등록위해 주석처리함
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //memoryRepository를 외부에서 넣어준다. 이를 Dependency Injection(DI)라고 한다.
    }

    //오.. /**엔터 -> 파라미터랑 리턴값 다 지정할 수 있게 해주네
    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member){
        //같은 이름이 있으면 안된다.
//        Optional<Member> result = memberRepository.findByName(member.getName());// ctrl alt v : 리턴값을 변수에 담아줌 (혹은 alt enter enter)
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });
        validateDuplicateMember(member); //중복회원 검증.
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다."); //exception 발생 -> 종료(?)
                }); // -> 메서드로 뽑는 것이 좋다. : ctrl shift alt t -> extract method
    }

    /**
     * 전체 회원조회
     * @return
     */
    public List<Member> findMembers(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }



}
