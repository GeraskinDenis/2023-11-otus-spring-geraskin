package ru.geraskindenis.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.geraskindenis.config.TestFileNameProvider;
import ru.geraskindenis.dao.dto.QuestionDto;
import ru.geraskindenis.domain.Question;
import ru.geraskindenis.exceptions.QuestionReadException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CsvQuestionDao implements QuestionDao {

    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {

        String csvFileName = fileNameProvider.getTestFileName();
        List<Question> questions;
        try (InputStream inputStream = Objects.requireNonNull(getClass()
                .getClassLoader().getResourceAsStream(csvFileName));
             InputStreamReader reader = new InputStreamReader(inputStream)) {
            questions = new CsvToBeanBuilder<>(reader).withType(QuestionDto.class)
                    .withSkipLines(1).withSeparator(';').build().stream().map((e) -> ((QuestionDto) e).toDomainObject())
                    .collect(Collectors.toList());
        } catch (IOException | RuntimeException e) {
            throw new QuestionReadException(String.format("Error reading file '%s'.", csvFileName), e);
        }
        return questions;
    }
}