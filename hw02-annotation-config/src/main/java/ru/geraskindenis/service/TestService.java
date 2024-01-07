package ru.geraskindenis.service;

import ru.geraskindenis.domain.Student;
import ru.geraskindenis.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
