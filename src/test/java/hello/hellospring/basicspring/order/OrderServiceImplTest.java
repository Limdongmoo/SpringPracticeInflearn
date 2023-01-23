package hello.hellospring.basicspring.order;

import hello.hellospring.basicspring.discount.FixDiscountPolicy;
import hello.hellospring.basicspring.member.Grade;
import hello.hellospring.basicspring.member.Member;
import hello.hellospring.basicspring.member.MemoryMemberRepository;
import hello.hellospring.basicspring.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        memberRepository.save(new Member(1L, "itemA", Grade.VIP));
        orderService.createOrder(1L, "itemA", 10000);
    }

}
