package com.onestore.serviceImplementation;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.exception.ProductException;
import com.onestore.model.CurrentUserSession;
import com.onestore.model.Customer;
import com.onestore.model.Product;
import com.onestore.repository.CustomerDao;
import com.onestore.repository.ProductDao;
import com.onestore.repository.UserSessionDao;
import com.onestore.service.ProductService;


@Service
public class ProductServiceImplementation implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserSessionDao currentuser;
	
	@Autowired
	private CustomerDao custDao;
	
	
	
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
	public Product addProductService(Product product,String key) throws ProductException, LoginException, CustomerException {
		
		
		CurrentUserSession status = currentuser.findByUuid(key);
		
		if(status==null) {
			
			throw new LoginException("Customer not logged in");
			
		}
		
		if(status.getRole().equalsIgnoreCase("customer")) {
			
			throw new CustomerException("You are not authorized to add products");
			
		}
		
		
		return  productDao.save(product);
		
	}

	@Override
	public Product updateProductService(Product product,String key) throws ProductException, CustomerException {
		
		
		CurrentUserSession status = currentuser.findByUuid(key);
		
			if(status==null) {
			
				throw new LoginException("Customer not logged in");
			
			}
			
			
			if(status.getRole().equalsIgnoreCase("customer")) {
				
				throw new CustomerException("You are not authorized to add products");
				
			}
		
		
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
	public Product removeProductService(Integer id,String key) throws ProductException, CustomerException {
		
		
		CurrentUserSession status = currentuser.findByUuid(key);
		
		if(status==null) {
		
			throw new LoginException("Customer not logged in");
		
		}
		
		
		if(status.getRole().equalsIgnoreCase("customer")) {
			
			throw new CustomerException("You are not authorized to add products");
			
		}
	
		
		
		
		Optional<Product> pro = productDao.findById(id);
		
		if(pro.isPresent()) {
			
			productDao.delete(pro.get());
			
			return pro.get();
			
		}
		
		throw new ProductException("Product not found with the id :"+id);
	}
	
		
	
	
}