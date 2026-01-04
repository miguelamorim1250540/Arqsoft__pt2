package com.isep.lendings.query.api;

import org.springframework.stereotype.Component;

import com.isep.lendings.query.model.LendingQueryModel;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LendingViewMapper {

    public LendingView toLendingView(LendingQueryModel lending) {

        return new LendingView(
                lending.getLendingNumber(),
                lending.getBookId(),
                lending.getBookTitle(),
                lending.getReaderNumber(),
                lending.getStartDate(),
                lending.getLimitDate(),
                lending.getReturnedDate(),
                lending.getDaysUntilReturn(),
                lending.getDaysOverdue(),
                lending.getFineValueInCents(),
                lending.getCommentary(),
                lending.getRating()
        );
    }

    public List<LendingView> toLendingView(List<LendingQueryModel> lendings) {
        return lendings.stream()
                .map(this::toLendingView)
                .collect(Collectors.toList());
    }
}
