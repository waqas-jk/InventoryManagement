package com.inventory.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.management.dao.ProductRepository;
import com.inventory.management.dao.ProductSizeMappingRepository;
import com.inventory.management.model.Product;
import com.inventory.management.model.ProductSizeMapping;

/**
 * @author Waqas
 */

@Service
public class ProductService {

	private ProductRepository productRepository;
	private ProductSizeMappingRepository mappingRepository;

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Autowired
	public void setProductSizeMappingRepository(ProductSizeMappingRepository mappingRepository) {
		this.mappingRepository = mappingRepository;
	}

	/**
	 * Get all products
	 * 
	 * @return
	 */
	public List<Product> getAllProducts() {
		return productRepository.findAllProducts(Boolean.FALSE);
	}

	/**
	 * Get all products
	 * 
	 * @return
	 */
	public List<Product> getAllProductsByBrand(Long brandId) {
		return productRepository.findAllProductsByBrand(brandId, Boolean.FALSE);
	}

	/**
	 * Get all products
	 * 
	 * @return
	 */
	public List<Product> getAllProductsByType(Long productType) {
		return productRepository.findAllProductsByType(productType, Boolean.FALSE);
	}

	/**
	 * Get product based on id
	 * 
	 * @return
	 */
	public Product getProduct(Long id) {
		return productRepository.findOne(id);
	}

	/**
	 * Save or Update product
	 * 
	 * @return
	 */
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	/**
	 * Mark the product as DELETED.
	 * 
	 * @return
	 */
	public Product deleteProduct(Long id) {
		Product product = getProduct(id);
		product.setIsDeleted(Boolean.TRUE);
		return saveProduct(product);
	}

	public List<ProductSizeMapping> getProductSizes(Long productId) {
		return mappingRepository.findSizesByProduct(productId);
	}

	public ProductSizeMapping saveProductSizeMapping(ProductSizeMapping mapping) {
		return mappingRepository.save(mapping);
	}

}
