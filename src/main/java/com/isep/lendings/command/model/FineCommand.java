package com.isep.lendings.command.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name="fines")
public class FineCommand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero
    private int fineValuePerDayInCents;

    @PositiveOrZero
    private int centsValue;

    @ManyToOne(optional = false)
    @JoinColumn(name="lending_id")
    private Lending lending;

    protected FineCommand() {}

    public FineCommand(Lending lending) {
        this.lending = lending;
        this.fineValuePerDayInCents = lending.getFineValuePerDayInCents();
        this.centsValue = fineValuePerDayInCents * lending.getDaysDelayed();
    }

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public int getFineValuePerDayInCents() {
        return fineValuePerDayInCents;
    }

    public int getCentsValue() {   // <- isso é que faltava
        return centsValue;
    }

    public Lending getLending() {
        return lending;
    }
}
