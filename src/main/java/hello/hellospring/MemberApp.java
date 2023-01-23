package hello.hellospring;

import hello.hellospring.basicspring.member.Grade;
import hello.hellospring.basicspring.member.Member;
import hello.hellospring.basicspring.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        System.out.println("memberService.findMember(1L) = " + memberService.findMember(1L));


    }
}
