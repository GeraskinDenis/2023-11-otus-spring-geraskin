package ru.geraskindenis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestRunnerServiceImpl implements TestRunnerService {

    @Autowired
    private final TestService testService;
    @Autowired
    private final StudentService studentService;
    @Autowired
    private final ResultService resultService;

    @Override
    public void run() {
        var student = studentService.determineCurrentStudent();
        var testResult = testService.executeTestFor(student);
        resultService.showResult(testResult);
    }
}
