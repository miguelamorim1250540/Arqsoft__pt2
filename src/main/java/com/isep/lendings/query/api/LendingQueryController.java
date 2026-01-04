package com.isep.lendings.query.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.isep.lendings.query.model.LendingQueryModel;
import com.isep.lendings.query.services.LendingServiceImpl;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Tag(name = "Lendings (Query)", description = "Endpoints for querying Lendings")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/query/lendings")
public class LendingQueryController {

    private final LendingServiceImpl lendingService;
    private final LendingViewMapper lendingViewMapper;

    @Operation(summary = "Get Lending by lending number")
    @GetMapping("/{year}/{seq}")
    public ResponseEntity<LendingView> getLendingByNumber(
            @PathVariable Integer year,
            @PathVariable Integer seq) {

        String lendingNumber = year + "/" + seq;

        LendingQueryModel lending = lendingService.findByLendingNumber(lendingNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Lending not found: " + lendingNumber));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(lendingViewMapper.toLendingView(lending));
    }

    @Operation(summary = "List overdue lendings")
    @GetMapping("/overdue")
    public List<LendingView> getOverdueLendings() {

        List<LendingQueryModel> overdue = lendingService.getOverdue();

        if (overdue.isEmpty())
            throw new ResponseStatusException(NOT_FOUND, "No overdue lendings found");

        return lendingViewMapper.toLendingView(overdue);
    }

    @Operation(summary = "Get average lending duration")
    @GetMapping("/avgDuration")
    public ResponseEntity<Double> getAverageDuration() {
        return ResponseEntity.ok(lendingService.getAverageDuration());
    }
}
