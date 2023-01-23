package hello.hellospring.basicspring.autowired;

import hello.hellospring.AutoAppConfig;
import hello.hellospring.basicspring.discount.DiscountPolicy;
import hello.hellospring.basicspring.member.Grade;
import hello.hellospring.basicspring.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        Assertions.assertThat(discountPrice).isEqualTo(1000);

        Member member1 = new Member(2L, "userB", Grade.VIP);
        int rateDiscountPolicy = discountService.discount(member1, 20000, "rateDiscountPolicy");

        Assertions.assertThat(rateDiscountPolicy).isEqualTo(2000);

    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String policy) {
            DiscountPolicy discountPolicy = this.policyMap.get(policy);
            return discountPolicy.discount(member, price);
        }
    }
}
