package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository // 스프링 시작될 때, 자동으로 MemoryMemberRepository를 컨테이너에 올려준다. //자바코드로 직접 빈 등록위해 주석처리함
//@Repository는 @Component를 가지고 있다 -> 컴포넌트 스캔이다.
public class MemoryMemberRepository implements MemberRepository{
    //이 코드가 실제로 작동하는지 확인 : 테스트케이스 작성해서 확인한다.
    //코드를 코드를 통해 검증

    private static Map<Long,Member> store = new HashMap<>(); //동시성문제?
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 추후 설명
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        /*
        Stream : 약간 파이썬의 iterator자료형이라고 보면 되려나..
        아무튼
        filter : 중개연산, 인자로 들어온 람다함수의 결과가 True인 것들만 모아 새로운 Stream생성, 반환
        findAny : 최종연산, 해당 Stream의 첫 객체를 반환한다. Optioanl로 반환한다.
         */
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
