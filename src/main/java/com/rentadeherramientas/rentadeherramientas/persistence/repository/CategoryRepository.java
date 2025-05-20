package com.appsecurity.app_security.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsecurity.app_security.persistence.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
