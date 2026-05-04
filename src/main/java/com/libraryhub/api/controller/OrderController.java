package com.libraryhub.api.controller;

import com.libraryhub.api.entity.Order;
import com.libraryhub.api.repository.BookRepository;
import com.libraryhub.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        if (order.getBookIds() != null) {
            for (Long bookId : order.getBookIds()) {
                bookRepository.findById(bookId).ifPresent(book -> {
                    if (book.getCopiesCount() > 0) {
                        book.setCopiesCount(book.getCopiesCount() - 1);
                        bookRepository.save(book);
                    }
                });
            }
        }

        return orderRepository.save(order);
    }

    @PatchMapping("/{id}/complete")
    public Order completeOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus("Виконано");
        return orderRepository.save(order);
    }
}