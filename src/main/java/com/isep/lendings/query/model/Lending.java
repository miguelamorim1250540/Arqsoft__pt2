// package com.isep.lendings.query.model;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.Max;
// import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// import java.time.LocalDate;
// import java.time.temporal.ChronoUnit;
// import java.util.Optional;
// import java.util.Objects;

// @Getter
// @Setter
// @NoArgsConstructor
// @Entity
// @Table(name = "lendings", uniqueConstraints = {@UniqueConstraint(columnNames={"lendingNumber"})})
// public class Lending {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long pk;

//     @NotNull
//     @Column(nullable = false, unique = true, updatable = false)
//     private String lendingNumber;

//     // 🔥 Agora são só IDs
//     @NotNull
//     @Column(nullable = false)
//     private String bookId;

//     @NotNull
//     @Column(nullable = false)
//     private String readerId;

//     @NotNull
//     @Column(nullable = false, updatable = false)
//     private LocalDate startDate;

//     @NotNull
//     @Column(nullable = false)
//     private LocalDate limitDate;

//     private LocalDate returnedDate;

//     @Version
//     private long version;

//     @Size(min = 0, max = 1024)
//     private String commentary;

//     @Min(0)
//     @Max(10)
//     private Integer rating;

//     @Transient
//     private Integer daysUntilReturn;

//     @Transient
//     private Integer daysOverdue;

//     private int fineValuePerDayInCents;

//     // ▶️ Construtor atualizado
//     public Lending(String bookId, String readerId, String lendingNumber, int lendingDuration, int fineValuePerDayInCents) {
//         this.bookId = Objects.requireNonNull(bookId);
//         this.readerId = Objects.requireNonNull(readerId);
//         this.lendingNumber = lendingNumber;
//         this.startDate = LocalDate.now();
//         this.limitDate = startDate.plusDays(lendingDuration);
//         this.fineValuePerDayInCents = fineValuePerDayInCents;
//         setDaysUntilReturn();
//         setDaysOverdue();
//     }

//     public void setReturned(long desiredVersion, String commentary, Integer rating) {
//         if (this.returnedDate != null) throw new IllegalStateException("Book already returned");
//         if (this.version != desiredVersion) throw new OptimisticLockException("Object was modified");

//         this.returnedDate = LocalDate.now();
//         this.commentary = commentary;
//         this.rating = rating;
//     }

//     private void setDaysUntilReturn() {
//         int days = (int) ChronoUnit.DAYS.between(LocalDate.now(), this.limitDate);
//         this.daysUntilReturn = (this.returnedDate != null || days < 0) ? null : days;
//     }

//     private void setDaysOverdue() {
//         int days = getDaysDelayed();
//         this.daysOverdue = days > 0 ? days : null;
//     }

//     public int getDaysDelayed() {
//         if (returnedDate != null)
//             return Math.max((int) ChronoUnit.DAYS.between(limitDate, returnedDate), 0);

//         return Math.max((int) ChronoUnit.DAYS.between(limitDate, LocalDate.now()), 0);
//     }

//     public Optional<Integer> getDaysUntilReturn() {
//         setDaysUntilReturn();
//         return Optional.ofNullable(daysUntilReturn);
//     }

//     public Optional<Integer> getDaysOverdue() {
//         setDaysOverdue();
//         return Optional.ofNullable(daysOverdue);
//     }

//     public Optional<Integer> getFineValueInCents() {
//         int days = getDaysDelayed();
//         return days > 0 ? Optional.of(fineValuePerDayInCents * days) : Optional.empty();
//     }
// }
