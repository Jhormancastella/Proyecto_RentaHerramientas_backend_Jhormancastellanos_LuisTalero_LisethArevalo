package com.appsecurity.app_security.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsecurity.app_security.persistence.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
