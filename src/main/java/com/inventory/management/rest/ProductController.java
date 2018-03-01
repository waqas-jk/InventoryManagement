package com.inventory.management.rest;

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
import com.inventory.management.services.ProductService;
import com.inventory.management.services.ReferenceEntityService;

@RestController
@RequestMapping("/product")
public class ProductController {

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
	public List<Product> listAllProducts() {

		return productService.getAllProducts();
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "view/{productId}", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public Product getProductById(@PathVariable("productId") Long productId) {

		Product product = productService.getProduct(productId);
		if (product == null) {
			throw new EntityNotFoundException("Requested product can not be found.");
		}

		return product;
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "delete/{productId}", method = RequestMethod.PUT, produces = Constants.CONTENT_TYPE_JSON)
	public Product deleteProduct(@PathVariable("productId") Long productId) {

		Product product = productService.getProduct(productId);
		if (product == null) {
			throw new EntityNotFoundException("Requested product can not be found.");
		}

		return productService.deleteProduct(productId);
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "edit/{productId}", method = RequestMethod.PUT, consumes = Constants.CONTENT_TYPE_JSON, produces = Constants.CONTENT_TYPE_JSON)
	public Product editProduct(@Valid @RequestBody Product product, @PathVariable("productId") Long productId) {

		Product exisitngProduct = productService.getProduct(productId);

		if (exisitngProduct != null) {
			exisitngProduct.setName(product.getName());
			exisitngProduct.setDescription(product.getDescription());
			exisitngProduct.setBrand(refSerivce.getBrand(product.getBrand().getId()));
			exisitngProduct.setProductType(refSerivce.getProductType(product.getProductType().getId()));
			return productService.saveProduct(exisitngProduct);
		}

		throw new EntityNotFoundException("Requested product can not be found.");
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST, consumes = Constants.CONTENT_TYPE_JSON, produces = Constants.CONTENT_TYPE_JSON)
	public Product addNewProduct(@Valid @RequestBody Product product) {

		product.setBrand(refSerivce.getBrand(product.getBrand().getId()));
		product.setProductType(refSerivce.getProductType(product.getProductType().getId()));
		return productService.saveProduct(product);
	}

}
