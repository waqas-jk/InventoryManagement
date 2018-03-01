package com.inventory.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.management.dao.BrandRepository;
import com.inventory.management.dao.CountryRepository;
import com.inventory.management.dao.ProductSizeRepository;
import com.inventory.management.dao.ProductTypeRepository;
import com.inventory.management.model.Brand;
import com.inventory.management.model.reference.Country;
import com.inventory.management.model.reference.ProductSize;
import com.inventory.management.model.reference.ProductType;

/**
 * @author Waqas
 */

@Service
public class ReferenceEntityService {

	private ProductSizeRepository productSizeRepository;
	private ProductTypeRepository productTypeRepository;
	private CountryRepository countryRepository;
	private BrandRepository brandRepository;

	@Autowired
	public void setProductSizeRepository(ProductSizeRepository productSizeRepository) {
		this.productSizeRepository = productSizeRepository;
	}

	@Autowired
	public void setProductTypeRepository(ProductTypeRepository productTypeRepository) {
		this.productTypeRepository = productTypeRepository;
	}

	@Autowired
	public void setCountryRepository(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@Autowired
	public void setBrandRepository(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	/**
	 * Get all product sizes
	 * 
	 * @return
	 */
	public List<ProductSize> getAllProductSizes() {
		return productSizeRepository.findAll();
	}

	/**
	 * Get product size using id
	 * 
	 * @param id
	 * @return
	 */
	public ProductSize getProductSize(Long id) {
		return productSizeRepository.findOne(id);
	}

	public ProductSize saveProductSize(ProductSize productSize) {
		return productSizeRepository.save(productSize);
	}

	/**
	 * Get all product types
	 * 
	 * @return
	 */
	public List<ProductType> getAllProductTypes() {
		return productTypeRepository.findAll();
	}

	/**
	 * Get product type using id
	 * 
	 * @param id
	 * @return
	 */
	public ProductType getProductType(Long id) {
		return productTypeRepository.findOne(id);
	}

	/**
	 * Get all countries
	 * 
	 * @return
	 */
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}

	/**
	 * Get country based on id
	 * 
	 * @param id
	 * @return
	 */
	public Country getCountry(Long id) {
		return countryRepository.findOne(id);
	}

	/**
	 * Get brand based on id
	 * 
	 * @param id
	 * @return
	 */
	public Brand getBrand(Long id) {
		return brandRepository.findOne(id);
	}

}
