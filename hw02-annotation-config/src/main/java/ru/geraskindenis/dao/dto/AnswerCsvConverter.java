package ru.geraskindenis.dao.dto;

import com.opencsv.bean.AbstractCsvConverter;
import ru.geraskindenis.domain.Answer;

public class AnswerCsvConverter extends AbstractCsvConverter {
    @Override
    public Answer convertToRead(String s) {
        var valueArr = s.split("%");
        return new Answer(valueArr[0], Boolean.parseBoolean(valueArr[1]));
    }
}
