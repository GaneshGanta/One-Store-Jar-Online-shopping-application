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
	private ProductDao productRepo;
	
	@Autowired
	private CartDao cartD;
	
	@Autowired
	private UserSessionDao currentuser;
	
	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private Validation valid;
	
	
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

	
	@Override   ///this method is not working only other are working......................
	public ProductDto removeproductFromCart(Integer  pDtoId, String key) throws CustomerException, LoginException,CartException, ProductException{
		
		return null;
		
	}
	
	
	
	
	
	

	@Override
	public ProductDto updateProductQuantity(Integer pDtoId, Integer quantity, String key) throws CustomerException,LoginException {

		ProductDto product =null;        
		CurrentUserSession CurrentUserSession = currentuser.findByUuid(key);
		
		if(CurrentUserSession==null)
		{
			throw new CustomerException("You must login first");
		}
		
		
		
		
			if(CurrentUserSession.getRole().equalsIgnoreCase("Customer"))
			{
				Customer customer = valid.validateLogin(key);
				
				List<ProductDto> productDtolist =    customer.getCart().getProducts();
				
				
				
				
				boolean flag=false;
				
				for(int i=0;i<productDtolist.size();i++)
				{
					if(productDtolist.get(i).getId()==pDtoId)
					{
						productDtolist.get(i).setQuantity(productDtolist.get(i).getQuantity()+quantity);
						
						product =   productDtolist.get(i);
						flag = true;
					    break;
					}
				}
				
				if(flag==false)throw new CustomerException("Product not found with productDtoId: "+pDtoId);
				
			Cart customerCart =	 customer.getCart();
			customerCart.setProducts(productDtolist);
			
			customer.setCart(customerCart);
			custDao.save(customer);
				
			}
			else
			{
				throw new CustomerException("You cannot to this task because you are Admin.");
			}
			
			return product;
		}
	

	@Override
	public List<ProductDto> viewAllProductsFromCart(String key) throws CustomerException, LoginException {
		Customer customer = valid.validateLogin(key);
		  
	
			Cart cart=null;
			if(customer!=null)
			{
			    cart  =customer.getCart();
		
			}
			if(cart==null)
			{
				throw new CustomerException("Your cart is empty with cardId:"+cart.getCartId());
			}
			else
			{
				 return cart.getProducts();
			}
		
	}


	@Override
	public double cartTotal(String key)throws CustomerException, LoginException, ProductException{
		
		Customer customer = valid.validateLogin(key);
		
		
		 List<ProductDto> productList= customer.getCart().getProducts();
		
		double price = 0;
		
		if(productList.size()==0)
		{
			throw new ProductException("No product available inside your cart-->cartId:"+customer.getCart().getCartId());
		}
		else
		{
			for(ProductDto prod: productList)
			{
				 price = price +  prod.getPrice();
			}
		}
		
		
		return price;
	}

}
