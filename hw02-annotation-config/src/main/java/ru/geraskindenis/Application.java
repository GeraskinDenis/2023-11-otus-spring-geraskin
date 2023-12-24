package ru.geraskindenis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.geraskindenis.service.TestRunnerService;
import ru.geraskindenis.service.TestRunnerServiceImpl;

@ComponentScan
@Configuration
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        TestRunnerService runnerService = context.getBean(TestRunnerServiceImpl.class);
        runnerService.run();
    }
}
