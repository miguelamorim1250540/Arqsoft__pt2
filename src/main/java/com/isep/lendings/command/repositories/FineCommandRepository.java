package com.isep.lendings.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isep.lendings.command.model.FineCommand;
import java.util.Optional;

public interface FineCommandRepository extends JpaRepository<FineCommand, Long> {

    Optional<FineCommand> findByLending_LendingNumber(String lendingNumber);
}
