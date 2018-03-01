package com.inventory.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.management.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
