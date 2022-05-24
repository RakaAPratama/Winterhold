package com.winterhold.mvc.repositories;

import com.winterhold.mvc.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Page<Category> findByIdContaining(String name, Pageable pageable);
}
