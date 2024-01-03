package ru.geraskindenis.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Data
public class TestResult {

    private final Student student;

    private final Map<Question, Answer> testResult;
    
    private final int rightAnswersCount;
}
