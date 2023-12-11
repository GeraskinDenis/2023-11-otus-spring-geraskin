package ru.geraskindenis.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppProperties implements TestFileNameProvider {
    private String testFileName;

    @Override
    public String getTestFileName() {
        return testFileName;
    }
}
