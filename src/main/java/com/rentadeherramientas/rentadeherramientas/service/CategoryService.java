package com.appsecurity.app_security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.appsecurity.app_security.dto.SaveCategory;
import com.appsecurity.app_security.persistence.entity.Category;

import java.util.Optional;


public interface CategoryService {
    Page<Category> findAll(Pageable pageable);

    Optional<Category> findOneById(Long categoryId);

    Category createOne(SaveCategory saveCategory);

    Category updateOneById(Long categoryId, SaveCategory saveCategory);

    Category disableOneById(Long categoryId);
}
