package ru.geraskindenis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties implements TestConfig, TestFileNameProvider {

    private final int rightAnswersCountToPass;

    private final int maxAttempts;

    private final String testFileName;

    public AppProperties(@Value("${test.rightAnswersCountToPass}") int rightAnswersCountToPass,
                         @Value("${test.maxAttempts}") int maxAttempts,
                         @Value("${test.fileName}") String testFileName) {
        this.rightAnswersCountToPass = rightAnswersCountToPass;
        this.maxAttempts = maxAttempts;
        this.testFileName = testFileName;
    }

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    @Override
    public int getMaxAttempts() {
        return maxAttempts;
    }

    @Override
    public String getTestFileName() {
        return testFileName;
    }
}
