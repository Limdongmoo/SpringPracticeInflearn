package hello.hellospring.basicspring.discount;

import hello.hellospring.basicspring.annotation.MainDiscountPolicy;
import hello.hellospring.basicspring.member.Grade;
import hello.hellospring.basicspring.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }
        return 0;
    }
}
