package com.isep.lendings.query.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.isep.lendings.query.model.FineQueryModel;
import java.util.Optional;

public interface FineQueryRepository extends MongoRepository<FineQueryModel, String> {

    Optional<FineQueryModel> findByLendingNumber(String lendingNumber);
}
