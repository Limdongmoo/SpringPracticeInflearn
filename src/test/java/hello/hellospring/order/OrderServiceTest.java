package hello.hellospring.order;

import hello.hellospring.AppConfig;
import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import hello.hellospring.member.MemberService;
import hello.hellospring.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;

        memberService.join(new Member(1L, "memberA", Grade.VIP));
        Order itemA = orderService.createOrder(memberId, "itemA", 20000);
        Assertions.assertThat(itemA.getDiscountPrice()).isEqualTo(2000);

    }
}
