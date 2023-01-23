package hello.hellospring.basicspring.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void stateFulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService bean = ac.getBean(StatefulService.class);
        StatefulService bean1 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 10000원 주문
        bean.order("userA", 10000);
        //ThreadB : B사용자가 20000원 주문
        bean1.order("userB", 20000);

        int price = bean.getPrice();
        System.out.println("price = " + price);
        //공유 필드로 인해 큰 오류 발생
        Assertions.assertThat(bean.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
