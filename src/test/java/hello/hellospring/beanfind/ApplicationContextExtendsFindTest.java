package hello.hellospring.beanfind;

import hello.hellospring.discount.DiscountPolicy;
import hello.hellospring.discount.FixDiscountPolicy;
import hello.hellospring.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상이면 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
        Assertions.assertThrows(
                NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class)
        );
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상이면 이름으로 검색하면 된다.")
    void findBeanByParentTypeBeanName() {
        org.assertj.core.api.Assertions.assertThat(ac.getBean("rateDiscountPolicy",DiscountPolicy.class)).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("하위 타입으로 조회")
    void findBeanByParentSubType() {
        org.assertj.core.api.Assertions.assertThat(
                ac.getBean(RateDiscountPolicy.class)).isInstanceOf(DiscountPolicy.class);

    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beans.size()).isEqualTo(2);
        for (String key : beans.keySet()) {
            System.out.println("key = " + key + " value = " + beans.get(key));
        }

    }

    @Test
    @DisplayName("Object 타입으로 모두 조회")
    void findAllBeanByObjectType() {
        Map<String, Object> beans = ac.getBeansOfType(Object.class);

        for (String key : beans.keySet()) {
            System.out.println("key = " + key + " value = " + beans.get(key));
        }
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
