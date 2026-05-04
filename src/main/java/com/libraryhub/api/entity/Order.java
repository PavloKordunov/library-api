package com.libraryhub.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerInfo;

    @Column(columnDefinition = "TEXT")
    private String itemsSummary;

    private Double totalAmount;

    private String status = "Нове";

    private LocalDateTime createdAt = LocalDateTime.now();

    @ElementCollection
    @CollectionTable(name = "order_books", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "book_id")
    private List<Long> bookIds;
}