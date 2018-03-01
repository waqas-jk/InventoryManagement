package com.inventory.management.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.management.Constants;
import com.inventory.management.model.Product;
import com.inventory.management.model.ProductSizeMapping;
import com.inventory.management.model.reference.ProductSize;
import com.inventory.management.services.ProductService;
import com.inventory.management.services.ReferenceEntityService;

@RestController
@RequestMapping("/size")
public class SizeController {

	private ProductService productService;
	private ReferenceEntityService refSerivce;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setReferenceEntityService(ReferenceEntityService refSerivce) {
		this.refSerivce = refSerivce;
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public List<ProductSize> listAllProducts() {

		return refSerivce.getAllProductSizes();
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "product/{productId}", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public List<ProductSizeMapping> getProductSizes(@PathVariable("productId") Long productId) {

		if (productService.getProduct(productId) == null) {
			throw new EntityNotFoundException("Requested product can not be found.");
		}

		List<ProductSizeMapping> sizeList = productService.getProductSizes(productId);
		if (sizeList == null) {
			return new ArrayList<>();
		}

		return sizeList;
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "add/product/{productId}", method = RequestMethod.PUT, produces = Constants.CONTENT_TYPE_JSON)
	public ProductSizeMapping addProductSize(@Valid @RequestBody ProductSize productSize,
			@PathVariable("productId") Long productId) {

		Product product = productService.getProduct(productId);

		if (product == null) {
			throw new EntityNotFoundException("Requested product can not be found.");
		}

		ProductSizeMapping mapping = new ProductSizeMapping();
		mapping.setProduct(product);
		mapping.setProductSize(refSerivce.getProductSize(productSize.getId()));

		return productService.saveProductSizeMapping(mapping);
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "edit/{productSizeId}", method = RequestMethod.PUT, consumes = Constants.CONTENT_TYPE_JSON, produces = Constants.CONTENT_TYPE_JSON)
	public ProductSize editProduct(@Valid @RequestBody ProductSize productSize,
			@PathVariable("productSizeId") Long productSizeId) {

		ProductSize exisitngSize = refSerivce.getProductSize(productSizeId);

		if (exisitngSize != null) {
			exisitngSize.setName(productSize.getName());
			return refSerivce.saveProductSize(exisitngSize);
		}

		throw new EntityNotFoundException("Requested product size can not be found. Create product size first");
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST, consumes = Constants.CONTENT_TYPE_JSON, produces = Constants.CONTENT_TYPE_JSON)
	public ProductSize addNewProductSize(@Valid @RequestBody ProductSize productSize) {

		return refSerivce.saveProductSize(productSize);
	}

}
