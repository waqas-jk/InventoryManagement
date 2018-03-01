package com.inventory.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.management.model.reference.ProductSize;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {

}
