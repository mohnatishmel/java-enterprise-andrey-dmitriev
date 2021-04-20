package by.itacademy.constant;

import by.itacademy.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public interface ApplicationConstant {

    ApplicationContext APPLICATION_CONTEXT =
            new AnnotationConfigApplicationContext(ApplicationConfig.class);
}
