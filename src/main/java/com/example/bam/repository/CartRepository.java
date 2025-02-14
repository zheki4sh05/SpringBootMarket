package com.example.bam.repository;

import com.example.bam.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
// @Repository annotation ?
public interface CartRepository extends JpaRepository<Cart, Long> {
}
