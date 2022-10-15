package hello.hellospring.order;

import hello.hellospring.discount.FixDiscountPolicy;
import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import hello.hellospring.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        memberRepository.save(new Member(1L, "itemA", Grade.VIP));
        orderService.createOrder(1L, "itemA", 10000);
    }

}