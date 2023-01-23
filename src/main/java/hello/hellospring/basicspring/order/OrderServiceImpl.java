package hello.hellospring.basicspring.order;

import hello.hellospring.basicspring.annotation.MainDiscountPolicy;
import hello.hellospring.basicspring.discount.DiscountPolicy;
import hello.hellospring.basicspring.member.Member;
import hello.hellospring.basicspring.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //Test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
