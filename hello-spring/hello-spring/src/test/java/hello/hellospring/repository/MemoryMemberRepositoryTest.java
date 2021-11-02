package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository(); //다형성..

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test // 이 어노테이션 붙임으로서 아래 메소드가 실행가능하게 된다.
    //본래 : 객체를 생성하기 전에는 main함수가 아닌이상 메소드를 실행할 수 없다.(내 생각)
    //이 Test 어노테이션이 이 역할을 해주는 것 같다.
    //내가 생각하는 Test어노테이션의 역할 : 해당 클래스에 대한 객체 하나 생성 -> 그 안에서 어노테이션 붙은 메소드들 실행.
    //따라서, repository라는 변수는 세 테스트에서 같이 사용된다.
    //따라서, findAll, findByname을 테스트하는데 있어서 에러가 난다.(같은 이름(spring1, spring2)의 객체가 이전 테스트메소드를 실행할 때 정의했으므로,
    //현 테스트메소드를 실행할 때 만든 것은 해당되지 않는다.
    // (filter로 된 것 중에서 가장 첫번째 객체를 가져오기에, 현 테스트메소드에서 만든 객체가 반환되는 것이 아니라, 이전 테스트케이스에서 만들어진 객체가 반환되는 것)
    //따라서 AfterEach어노테이션을 가진 메소드를 통해 한 테스트 메소드가 끝났을 시에 데이터를 클리어해주는 것이 필요하다.
    //테스트는 순서에 의존관계를 가지지 않도록 설계해야한다. -> 테스트마다 끝나고 공용 데이터를 클리어해주어야한다.
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById((member.getId())).get();
        //Assertions.assertThat(member).isEqualTo(result); -> alt enter -> static import시에, 메소드 자체가 임포트된다.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByname(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift f6 : 변수 이름 자동으로 바꿀 수 있음.
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        //Member result = repository.findByName("spring2").get(); // -> 에러난다.

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
