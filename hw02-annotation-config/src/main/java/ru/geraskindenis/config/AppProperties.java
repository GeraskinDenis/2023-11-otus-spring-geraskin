package ru.geraskindenis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class AppProperties implements TestFileNameProvider {

    private final String testFileName;

    public AppProperties(@Value("${test.fileName}") String testFileName) {
        this.testFileName = testFileName;
    }

    @Override
    public String getTestFileName() {
        return testFileName;
    }
}
