package hello.hellospring.controller;

//컨트롤러의 인자로 이 클래스 객체가 들어간다. -> 해당하는 템플릿의 form을 이 클래스 객체로 보내어 저장시킨다.(setter을 통해서 작동한다.)
//이를 시용하려면, 컨트롤러에서 이 값을 getter로 꺼내주면 된다.

public class MemberForm { //PostMapping으로 들어오는 것 처리해줄 예정.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
