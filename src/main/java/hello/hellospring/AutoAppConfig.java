package hello.hellospring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.hellospring",
        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 기존 예제코드의 Configuration 어노테이션을 제외하기 위해서 추가한 설정
)
public class AutoAppConfig {
}
