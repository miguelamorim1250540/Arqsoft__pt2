package com.isep.lendings.command.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isep.lendings.command.event.LendingCreatedEvent;
import com.isep.lendings.command.event.LendingReturnedEvent;
import com.isep.lendings.command.handlers.dto.SetLendingReturnedRequest;
import com.isep.lendings.command.messaging.LendingEventPublisher;
import com.isep.lendings.command.model.FineCommand;
import com.isep.lendings.command.model.Lending;
import com.isep.lendings.command.repositories.FineCommandRepository;
import com.isep.lendings.command.repositories.LendingCommandRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LendingServiceImpl {

    private final LendingCommandRepository lendingRepo;
    private final FineCommandRepository fineRepo;
    private final LendingEventPublisher publisher;

    private final int lendingDurationInDays = 14;
    private final int fineValuePerDayInCents = 50;

    // ---------- CREATE ----------
    @Transactional
    public Lending create(String bookId, String readerId) {

        String lendingNumber = generateLendingNumber();

        Lending lending = new Lending(
                bookId,
                readerId,
                lendingNumber,
                lendingDurationInDays,
                fineValuePerDayInCents
        );

        lendingRepo.save(lending);

        // 🔔 EVENTO
        publisher.publishCreated(
                new LendingCreatedEvent(
                        lending.getLendingNumber(),
                        lending.getBookId(),
                        lending.getReaderId(),
                        lending.getStartDate(),
                        lending.getLimitDate()
                )
        );

        return lending;
    }

    // ---------- SET RETURNED ----------
    @Transactional
    public Lending setReturned(String lendingNumber,
                               SetLendingReturnedRequest cmd,
                               long expectedVersion) {

        Lending lending = lendingRepo.findByLendingNumber(lendingNumber)
                .orElseThrow(() -> new RuntimeException("Lending not found"));

        lending.setReturned(expectedVersion, cmd.getCommentary());

        lendingRepo.save(lending);

        int fineValue = 0;

        if (lending.getDaysDelayed() > 0) {
            FineCommand fine = new FineCommand(lending);
            fineRepo.save(fine);
            fineValue = fine.getCentsValue();
        }

        // 🔔 EVENTO
        publisher.publishReturned(
                new LendingReturnedEvent(
                        lending.getLendingNumber(),
                        lending.getReturnedDate(),
                        fineValue
                )
        );

        return lending;
    }

    // ---------- HELPERS ----------
    private String generateLendingNumber() {
        int year = LocalDate.now().getYear();
        long seq = lendingRepo.count() + 1;
        return year + "/" + seq;
    }
}
