// package com.isep.lendings.query.model;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.PositiveOrZero;
// import lombok.Getter;
// import lombok.Setter;

// import java.util.Objects;

// @Getter
// @Entity(name = "FineQuery")
// @Table(name = "fines")
// public class FineQuery {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long pk;

//     @PositiveOrZero
//     @Column(updatable = false)
//     private int fineValuePerDayInCents;

//     @PositiveOrZero
//     private int centsValue;

//     @Setter
//     @OneToOne(optional = false, orphanRemoval = true)
//     @JoinColumn(name = "lending_pk", nullable = false, unique = true)
//     private Lending lending;

//     public FineQuery(Lending lending) {
//         if (lending.getDaysDelayed() <= 0)
//             throw new IllegalArgumentException("Lending is not overdue");

//         this.fineValuePerDayInCents = lending.getFineValuePerDayInCents();
//         this.centsValue = fineValuePerDayInCents * lending.getDaysDelayed();
//         this.lending = Objects.requireNonNull(lending);
//     }

//     protected FineQuery() {} // Para ORM
// }
