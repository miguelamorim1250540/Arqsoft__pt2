package com.isep.lendings.query.messaging;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.isep.lendings.query.event.LendingCreatedEvent;
import com.isep.lendings.query.event.LendingReturnedEvent;
import com.isep.lendings.query.model.FineQueryModel;
import com.isep.lendings.query.model.LendingQueryModel;
import com.isep.lendings.query.repositories.FineQueryRepository;
import com.isep.lendings.query.repositories.LendingQueryRepository;
import com.isep.lendings.shared.config.RabbitConfig;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LendingEventListener {

    private final LendingQueryRepository lendingRepo;
    private final FineQueryRepository fineRepo;

    /* ------------------ Lending CREATED ------------------ */
    @RabbitListener(queues = RabbitConfig.QUERY_QUEUE)
    public void handleCreated(LendingCreatedEvent event) {

        LendingQueryModel model = new LendingQueryModel();
        model.setLendingNumber(event.lendingNumber());
        model.setBookId(event.bookId());
        model.setBookTitle(event.bookTitle());
        model.setReaderId(event.readerId());
        model.setReaderNumber(event.readerNumber());
        model.setStartDate(event.startDate());
        model.setLimitDate(event.limitDate());
        model.setReturned(false);
        model.setOverdue(false);

        lendingRepo.save(model);
    }

    /* ------------------ Lending RETURNED ------------------ */
    @RabbitListener(queues = RabbitConfig.QUERY_QUEUE)
    public void handleReturned(LendingReturnedEvent event) {

        Optional<LendingQueryModel> opt = lendingRepo.findByLendingNumber(event.lendingNumber());
        if(opt.isPresent()) {
            LendingQueryModel model = opt.get();
            model.setReturned(true);
            model.setReturnedDate(event.returnedDate());
            model.setFineValueInCents(event.fineValueInCents());
            model.setOverdue(event.fineValueInCents() > 0);

            lendingRepo.save(model);

            // Criar ou atualizar FineQueryModel
            FineQueryModel fine = new FineQueryModel();
            fine.setLendingNumber(event.lendingNumber());
            fine.setFineValuePerDayInCents(event.fineValueInCents()); // podes ajustar se tiveres valor por dia
            fine.setTotalCentsValue(event.fineValueInCents());
            fineRepo.save(fine);
        }
    }
}
