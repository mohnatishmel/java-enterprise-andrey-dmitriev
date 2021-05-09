package by.itacademy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({PersistenceConfig.class, WebConfig.class})
@PropertySource("classpath:application.properties")
@ComponentScan({"by.itacademy.service", "by.itacademy.security", "by.itacademy.front"})
public class ApplicationConfig {
}
