package com.isep.lendings.command.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isep.lendings.command.handlers.CreateLendingHandler;
import com.isep.lendings.command.handlers.SetLendingReturnedHandler;
import com.isep.lendings.command.handlers.dto.CreateLendingCommand;
import com.isep.lendings.command.handlers.dto.SetLendingReturnedRequest;
import com.isep.lendings.command.model.Lending;


@RestController
@RequestMapping("/api/lendings")
@RequiredArgsConstructor
public class LendingCommandController {

    private final CreateLendingHandler createHandler;
    private final SetLendingReturnedHandler setReturnedHandler;

    // ---------------- CREATE Lending ----------------
    @PostMapping
    public ResponseEntity<Lending> create(@Valid @RequestBody CreateLendingCommand command) {
        Lending lending = createHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(lending);
    }

    // ---------------- SET Lending as returned ----------------
    @PatchMapping("/{year}/{seq}")
    public ResponseEntity<Lending> setReturned(
            @PathVariable int year,
            @PathVariable int seq,
            @RequestHeader("If-Match") long ifMatch,
            @Valid @RequestBody SetLendingReturnedRequest request) {

        String lendingNumber = year + "/" + seq;
        Lending lending = setReturnedHandler.handle(lendingNumber, ifMatch, request);
        return ResponseEntity.ok(lending);
    }
}