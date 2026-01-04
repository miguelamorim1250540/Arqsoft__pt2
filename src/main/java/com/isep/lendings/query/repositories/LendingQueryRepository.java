package com.isep.lendings.query.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.isep.lendings.query.model.LendingQueryModel;

public interface LendingQueryRepository extends MongoRepository<LendingQueryModel, String> {

    Optional<LendingQueryModel> findByLendingNumber(String lendingNumber);

    List<LendingQueryModel> findByReaderIdAndBookId(String readerId, String bookId);

    List<LendingQueryModel> findByReaderIdAndReturnedDateIsNullAndLimitDateBefore(String readerId, LocalDate date);

    List<LendingQueryModel> findByReturnedDateIsNullAndLimitDateBefore(LocalDate date);

    long countByStartDateBetween(LocalDate start, LocalDate end);
}

