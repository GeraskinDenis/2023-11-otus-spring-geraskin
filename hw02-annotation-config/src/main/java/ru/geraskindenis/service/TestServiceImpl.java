package ru.geraskindenis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geraskindenis.dao.QuestionDao;
import ru.geraskindenis.domain.Answer;
import ru.geraskindenis.domain.Question;
import ru.geraskindenis.domain.Student;
import ru.geraskindenis.domain.TestResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final QuestionDao questionDao;

    private final IOService ioService;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        List<Question> questions = questionDao.findAll();
        TestResult testResult = new TestResult(student);

        for (Question question : questions) {
            printQuestion(question);
            Answer answer = askQuestion(question);
            testResult.applyAnswer(question, answer.isCorrect());
        }
        return testResult;
    }

    private void printQuestion(Question question) {
        ioService.printLine("\n" + question.text());
        List<Answer> answers = question.answers();
        for (int i = 0; i < answers.size(); i++) {
            ioService.printFormattedLine("%d. %s", i + 1, answers.get(i).text());
        }
    }

    private Answer askQuestion(Question question) {
        List<Answer> answers = question.answers();
        int numberOfAnswer = ioService.readIntForRange(1, answers.size(), "Error message");
        return answers.get(numberOfAnswer - 1);
    }

}
