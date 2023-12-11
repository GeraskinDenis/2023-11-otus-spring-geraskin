package ru.geraskindenis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.geraskindenis.service.TestRunnerService;
import ru.geraskindenis.service.TestRunnerServiceImpl;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        TestRunnerService testRunnerService = context.getBean(TestRunnerServiceImpl.class);
        testRunnerService.run();
    }
}
