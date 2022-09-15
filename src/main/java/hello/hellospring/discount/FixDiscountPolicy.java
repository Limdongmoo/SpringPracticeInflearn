package hello.hellospring.discount;

import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {
    private final int discountFixAmount =1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        return 0;
    }
}
