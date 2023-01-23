package hello.hellospring;

import hello.hellospring.basicspring.discount.DiscountPolicy;
import hello.hellospring.basicspring.discount.RateDiscountPolicy;
import hello.hellospring.basicspring.member.MemberRepository;
import hello.hellospring.basicspring.member.MemberService;
import hello.hellospring.basicspring.member.MemberServiceImpl;
import hello.hellospring.basicspring.member.MemoryMemberRepository;
import hello.hellospring.basicspring.order.OrderService;
import hello.hellospring.basicspring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
