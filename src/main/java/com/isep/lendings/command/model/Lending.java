package com.isep.lendings.command.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@Table(name = "lendings")
public class Lending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String lendingNumber; // ex: "2025/1"

    @NotNull
    @Column(name = "book_id")
    private String bookId;

    @NotNull
    @Column(name = "reader_id")
    private String readerId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate limitDate;

    private LocalDate returnedDate;

    @Version
    private Long version;

    @Size(max = 1024)
    private String commentary;

    private int fineValuePerDayInCents;

    protected Lending() {}

    public Lending(
            String bookId,
            String readerId,
            String lendingNumber,
            int lendingDurationInDays,
            int fineValuePerDayInCents) {

        this.bookId = Objects.requireNonNull(bookId);
        this.readerId = Objects.requireNonNull(readerId);
        this.lendingNumber = lendingNumber;
        this.startDate = LocalDate.now();
        this.limitDate = startDate.plusDays(lendingDurationInDays);
        this.fineValuePerDayInCents = fineValuePerDayInCents;
        this.version = 0L;
    }

    public void setReturned(long expectedVersion, String commentary) {
        if (this.returnedDate != null)
            throw new IllegalStateException("Book already returned");
        if (!Objects.equals(this.version, expectedVersion))
            throw new IllegalStateException("Version conflict");

        this.returnedDate = LocalDate.now();
        this.commentary = commentary;
        this.version++;
    }

    public int getDaysDelayed() {
        LocalDate compareDate = returnedDate != null ? returnedDate : LocalDate.now();
        return (int) Math.max(ChronoUnit.DAYS.between(limitDate, compareDate), 0);
    }

    public Long getId() { return id; }
    public String getLendingNumber() { return lendingNumber; }
    public String getBookId() { return bookId; }
    public String getReaderId() { return readerId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getLimitDate() { return limitDate; }
    public LocalDate getReturnedDate() { return returnedDate; }
    public Long getVersion() { return version; }
    public String getCommentary() { return commentary; }
    public int getFineValuePerDayInCents() { return fineValuePerDayInCents; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setReturnedDate(LocalDate returnedDate) { this.returnedDate = returnedDate; }

    public static Lending newBootstrappingLending(
            String bookId,
            String readerId,
            int year,
            int seq,
            LocalDate startDate,
            LocalDate returnedDate,
            int lendingDurationInDays,
            int fineValuePerDayInCents) {

        String lendingNumber = year + "/" + seq;

        Lending lending = new Lending(bookId, readerId, lendingNumber,
                lendingDurationInDays, fineValuePerDayInCents);

        lending.setStartDate(startDate);
        lending.setReturnedDate(returnedDate);

        return lending;
    }
}
