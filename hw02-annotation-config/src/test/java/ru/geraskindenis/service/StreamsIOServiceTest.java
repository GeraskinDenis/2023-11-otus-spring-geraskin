package ru.geraskindenis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StreamsIOServiceTest {
    private IOService ioService;

    private final int MAX_ATTEMPTS = 10;

    @BeforeEach
    void beforeEach() {
        PrintStream printStream = Mockito.mock(PrintStream.class);
        InputStream inputStream = new ByteArrayInputStream(("6" + System.lineSeparator()).repeat(MAX_ATTEMPTS + 1)
                .getBytes(StandardCharsets.UTF_8));
        ioService = new StreamsIOService(printStream, inputStream, MAX_ATTEMPTS);
    }

    @Test
    @DisplayName("Get an IllegalArgumentException when there are more than one attempt to respond")
    void testCase1() {
        assertThatThrownBy(() -> {
            for (int i = 0; i < MAX_ATTEMPTS + 1; i++) {
                ioService.readIntForRange(1, 5, "Error message");
            }
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Error during reading int value");
    }
}
