package hello.hellospring.basicspring.member;


import hello.hellospring.AppConfig;
import hello.hellospring.basicspring.member.Grade;
import hello.hellospring.basicspring.member.Member;
import hello.hellospring.basicspring.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {


    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        memberService.join(member);
        Member member1 = memberService.findMember( 1L);
        //then
        Assertions.assertThat(member).isEqualTo(member1);
    }
}
