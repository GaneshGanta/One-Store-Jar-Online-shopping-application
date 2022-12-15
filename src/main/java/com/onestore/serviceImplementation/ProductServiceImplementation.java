package com.onestore.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onestore.exception.ProductException;
import com.onestore.model.Product;
import com.onestore.repository.ProductDao;
import com.onestore.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService{
	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> viewAllProductsService() throws ProductException {
		
		List<Product> products = productDao.findAll();
		
		if(products.isEmpty()) {
			
			throw new ProductException("empty list of products");
		}
		
		return products;
		
	}

	@Override
	public Product viewProductByIdSerivce(Integer id) throws ProductException {
		
		
		return productDao.findById(id).orElseThrow(()-> new ProductException("Product Not exists with the Id: "+id));
		
		
	}

	@Override
	public Product addProductService(Product product) throws ProductException {
		
		return  productDao.save(product);
		
	}

	@Override
	public Product updateProductService(Product product) throws ProductException {
		
		Optional<Product> pro = productDao.findById(product.getProductId());
		
		if(pro.isPresent()) {
			
			return productDao.save(product);
			
		}
		
		throw new ProductException("product not found with the Id: "+product.getProductId());
	}

	@Override
	public List<Product> viewProductByCategoryService(String c_name) throws ProductException {
		
		List<Product> products = productDao.findByCategory(c_name);
		
		if(products.isEmpty()) {
			
			throw new ProductException("empty list of the products");
			
		}
		
		return products;
	
	}

	@Override
	public Product removeProductService(Integer id) throws ProductException {
		
		Optional<Product> pro = productDao.findById(id);
		
		if(pro.isPresent()) {
			
			productDao.delete(pro.get());
			
			return pro.get();
			
		}
		
		throw new ProductException("Product not found with the id :"+id);
	}
	
		
	
	
}
