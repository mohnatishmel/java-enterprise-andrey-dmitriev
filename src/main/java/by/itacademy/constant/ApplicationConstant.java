package by.itacademy.constant;

import by.itacademy.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public interface ApplicationConstant {

    org.springframework.context.ApplicationContext APPLICATION_CONTEXT =
            new AnnotationConfigApplicationContext(ApplicationConfig.class);
}
