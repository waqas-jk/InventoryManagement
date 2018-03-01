package com.inventory.management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.management.model.ProductSizeMapping;

@Repository
public interface ProductSizeMappingRepository extends JpaRepository<ProductSizeMapping, Long> {

	@Query("from com.inventory.management.model.ProductSizeMapping p where product.id = :pId")
	public List<ProductSizeMapping> findSizesByProduct(@Param("pId") Long productId);
}
