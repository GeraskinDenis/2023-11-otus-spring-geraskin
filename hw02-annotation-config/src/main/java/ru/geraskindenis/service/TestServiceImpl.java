package ru.geraskindenis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geraskindenis.dao.QuestionDao;
import ru.geraskindenis.domain.Answer;
import ru.geraskindenis.domain.Question;
import ru.geraskindenis.domain.Student;
import ru.geraskindenis.domain.TestResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private final QuestionDao questionDao;
    @Autowired
    private final IOService streamIoService;

    @Override
    public TestResult executeTestFor(Student student) {
        List<Question> questions = questionDao.findAll();
        return testingProcess(student, questions);
    }

    private TestResult testingProcess(Student student, List<Question> questions) {
        String patternOfAnswer = "%d. %s";
        String patternOfPrompt = "Enter answer number (1 - %d):";
        Map<Question, Answer> testResult = new HashMap<>();
        int rightAnswersCount = 0;
        streamIoService.printLine("");
        streamIoService.printLine("Please, answer the questions bellow:");
        for (Question q : questions) {
            int numberOfRightAnswer = 0;
            streamIoService.printLine(q.text());
            List<Answer> answers = q.answers();
            for (int i = 0; i < answers.size(); i++) {
                Answer answer = answers.get(i);
                if (answer.isCorrect()) {
                    numberOfRightAnswer = i + 1;
                }
                streamIoService.printFormattedLine(patternOfAnswer, i + 1, answer.text());
            }
            int numberOfAnswer = streamIoService.readIntForRangeWithPrompt(1, answers.size(), String.format(patternOfPrompt, answers.size()), "Error message");

            if (numberOfAnswer == numberOfRightAnswer) {
                rightAnswersCount++;
            }

            testResult.put(q, answers.get(numberOfAnswer - 1));
        }
        return new TestResult(student, testResult, rightAnswersCount);
    }
}
