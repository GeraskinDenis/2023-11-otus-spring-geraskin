package ru.geraskindenis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geraskindenis.dao.QuestionDao;
import ru.geraskindenis.domain.Question;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private final QuestionDao questionDao;
    @Autowired
    private final IOService streamIoService;

    @Override
    public void executeTest() {
        streamIoService.printLine("");
        streamIoService.printFormattedLine("Please, answer the questions bellow%n");
        List<Question> questions = questionDao.findAll();
        questions.forEach((e) -> {
            streamIoService.printLine("");
            streamIoService.printFormattedLine(e.text());
            e.answers().forEach((answer) -> streamIoService.printLine(answer.text()));
        });
    }
}
