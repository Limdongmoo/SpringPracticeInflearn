package hello.hellospring.basicspring.singleton;

import hello.hellospring.AppConfig;
import hello.hellospring.basicspring.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("Spring 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //조회 1. 매 호출마다 객체 생성
        MemberService memberService = appConfig.memberService();
        //조회 2.매 호출마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        // memberService != memberService1
        Assertions.assertThat(memberService).isNotSameAs(memberService1);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체사용")
    void SingletonServiceTest() {
        SingletonService instance = SingletonService.getInstance();
        SingletonService instance1 = SingletonService.getInstance();

        Assertions.assertThat(instance).isSameAs(instance1);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void SpringContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //조회 1
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //조회 2
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // memberService != memberService1
        Assertions.assertThat(memberService).isSameAs(memberService1);

    }
}
