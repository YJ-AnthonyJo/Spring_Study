package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired //생략가능.
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //    private EntityManager em;
//    private DataSource dataSource;
//
//    public SpringConfig(EntityManager em, DataSource dataSource) {
//        this.em = em;
//        this.dataSource = dataSource;
//    }



//    private DataSource dataSource;
//
//    @Autowired //스프링이 알아서 DataSource는 가져다가 준다. //application.properties의 datasource를 기반으로
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }


    @Bean //아래 로직을 호출해 스프링 빈에 등록을 해준다.
   public MemberService memberService(){
//        return new MemberService(memberRepository()); //빈에 등록되어있는 MemberRepository를 넣어준다.
        return new MemberService(memberRepository); //spring jpa
        //Autowired와 비슷한 개념.
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); //기존에 이 MemoryMemberRepository를 사용했다.
//        return new JdbcMemberRepository(dataSource); //Jdbc로 바꾸어준다.
        //이 파일의 소스코드만 바꾸어서 Repository변환이 가능하다.
        //개방-폐쇄 원칙(OCP라고 한다.) : 확장에는 열려있고, 수정에는 닫혀있다.

//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
