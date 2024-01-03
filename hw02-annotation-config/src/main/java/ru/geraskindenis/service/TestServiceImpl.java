package ru.geraskindenis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geraskindenis.dao.QuestionDao;
import ru.geraskindenis.domain.Answer;
import ru.geraskindenis.domain.Question;
import ru.geraskindenis.domain.Student;
import ru.geraskindenis.domain.TestResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final QuestionDao questionDao;

    private final IOService streamIoService;

    @Override
    public TestResult executeTestFor(Student student) {
        List<Question> questions = questionDao.findAll();
        Map<Question, Answer> testResult = new HashMap<>();
        int rightAnswersCount = 0;
        streamIoService.printLine("/nPlease, answer the questions bellow:");
        for (Question q : questions) {
            int numberOfRightAnswer = 0;
            streamIoService.printLine(q.text());
            List<Answer> answers = q.answers();
            for (int i = 0; i < answers.size(); i++) {
                Answer answer = answers.get(i);
                if (answer.isCorrect()) {
                    numberOfRightAnswer = i + 1;
                }
                streamIoService.printFormattedLine("%d. %s", i + 1, answer.text());
            }
            int numberOfAnswer = streamIoService.readIntForRangeWithPrompt(1, answers.size(),
                    String.format("Enter answer number (1 - %d):", answers.size()), "Error message");
            if (numberOfAnswer == numberOfRightAnswer) {
                rightAnswersCount++;
            }
            testResult.put(q, answers.get(numberOfAnswer - 1));
        }
        return new TestResult(student, testResult, rightAnswersCount);
    }
}
