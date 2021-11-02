package hello.hellospring.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component //빈등록 : 일반적으로 bean직접 등록을 해준다.(특수한 케이스니까.) 지금은 편의 위해 컴포넌트 스캔으로 한다
@Aspect // AOP사용하기 위한 어노테이션
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))") //적용대상 지정. -> 공식 document보면 설명 잘 되어있다.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed(); //실제 메소드 실행.
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
                    "ms");
        }
    }
}