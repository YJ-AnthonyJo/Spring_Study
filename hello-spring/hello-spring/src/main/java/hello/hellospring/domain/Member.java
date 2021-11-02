package hello.hellospring.domain;

import javax.persistence.*;

@Entity // 이 어노테이션이 들어갔다 -> 이제 이 jpa가 관리하는 Entity가 되는 것이다.
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Id : PK를 의미.
    // Identity, DB가 자동으로 값을 넣어주는 것을 의미.
    private Long id; // 임의 값. 시스템이 정하는 id

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
