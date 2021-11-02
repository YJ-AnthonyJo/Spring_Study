package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em; //jpa는 이것으로 모든게 작동한다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 이 한줄로 insert 쿼리 다 만들어서 넣고, id까지 넣어준다.(setId까지)
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
        //"select m from Member m" 에서 Member m : Member as m의 약자.
        //Entity를 대상으로 Query를 날리는 것 -> 이를
//변수에 가져다 대고, ctrl alt n -> 코드가 중복되다면 제거해준다.(굳이 쓸데없는 변수로 지정해준다든지 등등.)
        //혹은 ctrl alt shift t 후 inline.
    }
}
