package com.isep.lendings.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isep.lendings.command.model.Lending;

import java.util.Optional;

@Repository
public interface LendingCommandRepository extends JpaRepository<Lending, Long> {
    Optional<Lending> findByLendingNumber(String lendingNumber);
    boolean existsByLendingNumber(String lendingNumber);
}
