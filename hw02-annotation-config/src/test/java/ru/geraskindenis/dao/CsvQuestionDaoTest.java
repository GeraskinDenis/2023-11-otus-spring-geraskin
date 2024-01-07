package ru.geraskindenis.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.geraskindenis.config.AppProperties;
import ru.geraskindenis.config.TestFileNameProvider;
import ru.geraskindenis.domain.Answer;
import ru.geraskindenis.domain.Question;
import ru.geraskindenis.exceptions.QuestionReadException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CsvQuestionDaoTest {
    private TestFileNameProvider testFileNameProvider;
    private QuestionDao questionDao;

    @BeforeEach
    void beforeEach() {
        testFileNameProvider = Mockito.mock(AppProperties.class);
        questionDao = new CsvQuestionDao(testFileNameProvider);
    }

    @Test
    @DisplayName("Should find all correct questions")
    void testCase1() {
        Mockito.doReturn("correctFormatQuestions.csv").when(testFileNameProvider).getTestFileName();
        List<Question> actual = questionDao.findAll();

        List<Question> expected = new ArrayList<>();
        expected.add(new Question("Question1",
                List.of(new Answer("Answer1", true), new Answer("Answer2", false), new Answer("Answer3", false))));
        expected.add(new Question("Question2",
                List.of(new Answer("Answer1", true), new Answer("Answer2", false), new Answer("Answer3", false))));
        expected.add(new Question("Question3",
                List.of(new Answer("Answer1", true), new Answer("Answer2", false), new Answer("Answer3", false))));

        assertThat(actual).containsAnyElementsOf(expected);
    }

    @Test
    @DisplayName("Should throw an Exception when reading a file of the wrong format")
    void testCase2() {
        Mockito.doReturn("incorrectFormatQuestions.csv").when(testFileNameProvider).getTestFileName();
        assertThatThrownBy(() -> questionDao.findAll()).isInstanceOf(QuestionReadException.class);
    }
}
