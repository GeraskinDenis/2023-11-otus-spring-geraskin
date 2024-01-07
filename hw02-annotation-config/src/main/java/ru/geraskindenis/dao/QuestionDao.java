package ru.geraskindenis.dao;

import ru.geraskindenis.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
