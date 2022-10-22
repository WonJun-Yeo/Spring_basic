package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulSingletonService() {
        //
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService StatefulService1 = ac.getBean(StatefulService.class);
        StatefulService StatefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자 10000원 주문
        StatefulService1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        StatefulService1.order("userB", 20000);

        // ThreadA: A사용자 주문 금액 조회
        int price = StatefulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(StatefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        //
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}