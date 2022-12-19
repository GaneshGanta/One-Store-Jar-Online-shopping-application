package com.onestore.serviceImplementation;

import java.lang.StackWalker.Option;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onestore.exception.CartException;
import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.exception.ProductException;
import com.onestore.model.Cart;
import com.onestore.model.CurrentUserSession;
import com.onestore.model.Customer;
import com.onestore.model.Product;
import com.onestore.model.ProductDto;
import com.onestore.repository.CartDao;
import com.onestore.repository.CustomerDao;
import com.onestore.repository.ProductDao;
import com.onestore.repository.ProductDtoDao;
import com.onestore.repository.UserSessionDao;
import com.onestore.service.CartServices;


@Service
public class CartServicesImpl implements CartServices{

	@Autowired
	private ProductDtoDao productDao;
	
	@Autowired
	private CartDao cartD;
	
	@Autowired
	private UserSessionDao currentuser;
	
	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private Validation valid;
	
	@Autowired
	private ProductDao productRepo;
	
	
//	@Override
//
//	public ProductDto updateProductQuantity(Integer pDtoId, Integer quantity, String key) throws CustomerException,LoginException {
//
//				Customer customer = valid.validateLogin(key);
//				if(customer==null)throw new CustomerException("customer not found with uuid:"+key);
//				
//				List<ProductDto> productDtolist =    customer.getCart().getProducts();
//				
//				ProductDto product = null;
//				
//				
//				boolean flag=false;
//				
//				for(int i=0;i<productDtolist.size();i++)
//				{
//					if(productDtolist.get(i).getId()==pDtoId)
//					{
//						productDtolist.get(i).setQuantity(productDtolist.get(i).getQuantity()+quantity);
//						
//						product =   productDtolist.get(i);
//						flag = true;
//					    break;
//					}
//				}
//				
//				if(flag==false)throw new CustomerException("Product not found with productDtoId: "+pDtoId);
//				
//			     Cart customerCart =	 customer.getCart();
//			      customerCart.setProducts(productDtolist);
//			
//			       customer.setCart(customerCart);
//			       custDao.save(customer);
//			       return product;
//			}
		
			



	@Override
	public List<ProductDto> viewAllProductsFromCart(String key) throws CustomerException, LoginException, ProductException {
		Customer customer = valid.validateLogin(key);
		 
	
			
			
			List<ProductDto> products = productDao.findAll();
			
			if(products.isEmpty()) {
				
				throw new ProductException("empty list of products");
			}
			
			return products;
			
		
	}

	
	@Override
	public Cart addProductToCart(Integer pid,Integer quantity, String key) throws CustomerException, LoginException, ProductException {
		   
		Optional<Product> prodopt =productRepo.findById(pid);
		
		Customer customer = valid.validateLogin(key);
		
		if(prodopt.isEmpty()) {
			throw new ProductException("Product Not Available!");
		}
		
		Cart cust_cart =  customer.getCart();
		
         Product product =prodopt.get();
         if(product.getQuantity()<quantity)
         {
        	 throw new ProductException("Only "+product.getQuantity()+"is available....");
         }
		
		
		
		List<ProductDto> existProducts = cust_cart.getProducts();
		
		boolean flag = false;
		for(ProductDto ele:existProducts) {
			if(ele.getProductId()==pid) {
				ele.setQuantity(ele.getQuantity()+quantity);
				flag = true;
				break;
			}
			
		}
		
		if(!flag) {
			ProductDto dto = new ProductDto();
			
		dto.setColor(product.getColor());
		dto.setDimension(product.getDimension());
		dto.setManufacturer(product.getManufacturer());
		dto.setPrice(product.getPrice());
		dto.setProductId(product.getProductId());
		dto.setProductName(product.getProductName());
		dto.setQuantity(quantity);
		cust_cart.getProducts().add(dto);
		}
		
		
		
		Cart updatedCart = cartD.save(cust_cart);
		
		customer.setCart(updatedCart);
		
		return updatedCart;
	}







	@Override
	public double cartTotal(String key)throws CustomerException, LoginException, ProductException{
		
		Customer customer = valid.validateLogin(key);
		
		
		double price = 0;
		
		
		List<ProductDto> products = productDao.findAll();
		
		if(products.isEmpty()) {
			
			throw new ProductException("empty list of products");
		}
		else
		{
			for(int i=0;i<products.size();i++)
			{
				price = price + products.get(i).getPrice();
			}
		}
		
		
		
		
		return price;
	}

}
