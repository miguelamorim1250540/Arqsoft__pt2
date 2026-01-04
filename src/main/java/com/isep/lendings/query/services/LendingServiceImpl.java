package com.isep.lendings.query.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.isep.lendings.query.model.LendingQueryModel;
import com.isep.lendings.query.repositories.LendingQueryRepository;
import com.isep.lendings.shared.domain.Page;
import com.isep.lendings.query.services.SearchLendingQuery;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service("lendingServiceQuery")
@RequiredArgsConstructor
public class LendingServiceImpl {

    private final LendingQueryRepository lendingRepository;

    public Optional<LendingQueryModel> findByLendingNumber(String lendingNumber){
        return lendingRepository.findByLendingNumber(lendingNumber);
    }

    public List<LendingQueryModel> listByReaderIdAndBookId(String readerId, String bookId, Optional<Boolean> returned){

        List<LendingQueryModel> lendings =
                lendingRepository.findByReaderIdAndBookId(readerId, bookId);

        returned.ifPresent(r ->
                lendings.removeIf(l -> (l.getReturnedDate() != null) != r)
        );

        return lendings;
    }

    public List<LendingQueryModel> getOverdue() {

        return lendingRepository
                .findByReturnedDateIsNullAndLimitDateBefore(LocalDate.now());
    }

    public Double getAverageDuration(){

        LocalDate start = LocalDate.of(LocalDate.now().getYear(),1,1);
        LocalDate end = LocalDate.of(LocalDate.now().getYear(),12,31);

        long count = lendingRepository.countByStartDateBetween(start, end);

        if(count == 0) return 0.0;

        // como não tens DATEDIFF em Mongo, calculas na app
        return lendingRepository.findAll().stream()
                .mapToLong(l -> java.time.temporal.ChronoUnit.DAYS
                        .between(l.getStartDate(), l.getLimitDate()))
                .average()
                .orElse(0.0);
    }
}