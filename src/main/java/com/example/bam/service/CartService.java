package com.example.bam.service;

import com.example.bam.entity.Cart;

import java.util.Optional;

public interface CartService {
    void addBookToCart(Long userId, Long bookId);
    Optional<Cart> findById(Long id);

    // unused method
    void deleteBookFromCart(Long userId, Long bookId);
}
