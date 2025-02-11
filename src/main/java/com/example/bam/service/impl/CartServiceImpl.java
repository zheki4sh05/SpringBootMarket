package com.example.bam.service.impl;

import com.example.bam.entity.Book;
import com.example.bam.entity.Cart;
import com.example.bam.entity.CartItem;
import com.example.bam.repository.BookRepository;
import com.example.bam.repository.CartItemRepository;
import com.example.bam.repository.CartRepository;
import com.example.bam.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void addBookToCart(Long userId, Long bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + bookId));

        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user id " + userId));

        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setCart(cart);

        cartItemRepository.save(cartItem);

        log.info("Book with id {} added to cart for user id {}", bookId, userId);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void deleteBookFromCart(Long userId, Long bookId) {

        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user id " + userId));

        CartItem cartItem = cartItemRepository.findByBookIdAndCartId(bookId, cart.getId())
                .orElseThrow(() -> new RuntimeException("CartItem not found for book id " + bookId + " in cart of user id " + userId));

        cartItemRepository.delete(cartItem);

        log.info("Book with id {} removed from cart for user id {}", bookId, userId);
    }

}

