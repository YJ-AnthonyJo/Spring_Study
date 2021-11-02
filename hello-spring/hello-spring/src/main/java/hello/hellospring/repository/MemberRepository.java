package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Generics(꺽새) : 이 Optional에 들어갈 수 있는 자료 한정(Member로) -> 즉 return가능 한 것이 Member뿐이다.
    Optional<Member> findByName(String name); //Generics(꺽새) : 이 Optional에 들어갈 수 있는 자료 한정(Member로) -> 즉 return가능 한 것이 Member뿐이다.
    List<Member> findAll();
}
