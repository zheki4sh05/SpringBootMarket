package com.example.bam.controller;

import com.example.bam.entity.Cart;
import com.example.bam.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add") // POST with body
    public ResponseEntity<String> addBookToCart(@RequestParam ("userId") Long userId,
                                                @RequestParam ("bookId") Long bookId) {
        try {
            cartService.addBookToCart(userId, bookId);
            return ResponseEntity.ok("Book added to cart successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //may be try like this:
//     @GetMapping("/{id}")
//    public ResponseEntity<PersonVO> getPersonById(@PathVariable Long id) {
//
//        return personService.getPersonById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/findCartById/{id}")
    public ResponseEntity<Optional<Cart>> findCartById(@PathVariable Long id) {
        Optional<Cart> cart = cartService.findById(id);
        return ResponseEntity.ok(cart);
    }

}
