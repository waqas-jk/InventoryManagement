package com.inventory.management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.management.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("from Product where isDeleted = :deleted")
	public List<Product> findAllProducts(@Param("deleted") Boolean isDeleted);

	@Query("from Product where brand.id = :brandId and isDeleted = :deleted")
	public List<Product> findAllProductsByBrand(@Param("brandId") Long brandId, @Param("deleted") Boolean isDeleted);

	@Query("from Product where productType.id = :productTypeId and isDeleted = :deleted")
	public List<Product> findAllProductsByType(@Param("productTypeId") Long productTypeId,
			@Param("deleted") Boolean isDeleted);

}
