package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //두번째 : id -> Long형태이다.
//JpaRepository를 extends하고 있으면, 자동으로, 구현체를 만들어준다... 와우

    //아래 findByName : 이 메소드 이름에 규칙이있다..
    //아래대로 하면 select m from Member m where m.name = ?으로 짜준다..
    //만일, findByNameAndId하면, select m from Member m where m.name=? and m.id=?으로 짜준다..
    //인터페이스이름만으로 끝난다.. 와..
    @Override
    Optional<Member> findByName(String name); // 끝났다. 더 구현할게 없다.
}
