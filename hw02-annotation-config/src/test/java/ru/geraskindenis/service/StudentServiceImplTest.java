package ru.geraskindenis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.geraskindenis.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentServiceImplTest {
    private StudentService studentService;

    @BeforeEach
    void beforeEach() {
        IOService ioService = Mockito.mock(StreamsIOService.class);
        Mockito.when(ioService.readStringWithPrompt("Please, input your first name:")).thenReturn("FirstName");
        Mockito.when(ioService.readStringWithPrompt("Please, input your last name:")).thenReturn("LastName");
        studentService = new StudentServiceImpl(ioService);
    }

    @Test
    @DisplayName("Successfully identify student")
    void testCase1() {
        Student actual = studentService.determineCurrentStudent();
        Student expected = new Student("FirstName", "LastName");
        assertThat(actual).isEqualTo(expected);
    }
}
