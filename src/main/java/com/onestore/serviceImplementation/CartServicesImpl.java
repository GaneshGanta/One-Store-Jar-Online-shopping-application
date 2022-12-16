package com.onestore.serviceImplementation;

import java.lang.StackWalker.Option;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.model.Cart;
import com.onestore.model.CurrentUserSession;
import com.onestore.model.Customer;
import com.onestore.model.Product;
import com.onestore.repository.CustomerDao;
import com.onestore.repository.ProductDao;
import com.onestore.repository.UserSessionDao;
import com.onestore.service.CartServices;


@Service
public class CartServicesImpl implements CartServices{

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserSessionDao currentuser;
	
	@Autowired
	private CustomerDao custDao;
	
	@Override
	public Product removeproductFromCart(Integer pid, String key, Integer cid,Integer quantity) throws CustomerException, LoginException{
		  
		Optional<Customer> opt = custDao.findById(cid);
		
//		login part started ----------------------------------------------------------------------------------------------------------------------------------------------------
		
		if(opt.isEmpty()) throw new CustomerException("Customer not found with id :"+cid);
		
		CurrentUserSession RunningSession =	currentuser.findByUuid(key);
		
		if(RunningSession ==null)
		{
			throw new LoginException("Please provide your valid Unique Login key ?");
		}
		if(RunningSession.getUserId()!=opt.get().getCustomerId())
		{
			throw new LoginException("please do Login first......");
		}
//		login part ended ----------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		if(opt.isPresent())
		{
			Customer customer =opt.get();
			
			Cart customer_cart =customer.getCart();
			
			List<Product>  productList =  customer_cart.getProducts();
			
			
			boolean flag = false;
			
			for(int i=0;i<productList.size();i++)
			{
				if(productList.get(i).getProductId()==pid)
				{
					productList.remove(productList.get(i));
					flag=true;
					break;
					
				}
			}
			
//		Here customer productlist get updated with new product....
			customer_cart.setProducts(productList);
			
			
			Optional<Product> optp = productDao.findById(pid);
			if(optp.isPresent())
			{
				Product product =optp.get();
				if(quantity<1)
				{
					throw new CustomerException("Please provide valid quantity");
				}
				else
				{
					  product.setQuantity(product.getQuantity()-quantity);
					  productDao.save(product);
				}
				
			}
			else
			{
				throw new CustomerException("Invalid product id:"+pid);
			}
			
		}
		
		
		throw new CustomerException("Invalid customerId:"+cid);
	}
	
	
	
	
	
	

	@Override
	public Product updateProductQuantity(Integer cid, Integer pid, Integer quantity, String key) throws CustomerException,LoginException {
		
	  Optional<Customer> opt =	custDao.findById(cid);
	  
	  if(opt.isEmpty()) throw new CustomerException("Customer not found with id:"+cid);
//		login part started ----------------------------------------------------------------------------------------------------------------------------------------------------
		
		if(opt.isEmpty()) throw new CustomerException("Customer not found with id :"+cid);
		
		CurrentUserSession RunningSession =	currentuser.findByUuid(key);
		
		if(RunningSession ==null)
		{
			throw new LoginException("Please provide your valid Unique Login key ?");
		}
		if(RunningSession.getUserId()!=opt.get().getCustomerId())
		{
			throw new LoginException("please do Login first......");
		}
//		login part ended ----------------------------------------------------------------------------------------------------------------------------------------------------
		
		if(opt.isPresent())
		{
		  Customer	cust=opt.get();
		  
		  Cart cust_cart =cust.getCart();
		  
		 List<Product> productList =cust_cart.getProducts();
		 
		 boolean flag=false;
		 
		 Product product =null;
		 
		 for(int i=0;i<productList.size();i++)
		 {
			 if(productList.get(i).getProductId()==pid)
			 {
				 productList.get(i).setQuantity(productList.get(i).getQuantity()+quantity);
				 
				 product =productList.get(i);
				 
				 flag = true;
				 break;
			 }
		 }
		 if(flag==false)throw new CustomerException("Product not found with productId: "+pid);
		 
		 cust_cart.setProducts(productList);
		 cust.setCart(cust_cart);
		 custDao.save(cust);
		  
		 return product ;
		  
		}
	  
		throw new CustomerException("Customer not found with customerId:"+cid);
	}

	@Override
	public String addProductToCart(Integer pid, Integer cid, String key) throws CustomerException, LoginException {
		    
		Optional<Product>prodopt =productDao.findById(pid);
		
		  Optional<Customer> custopt =custDao.findById(cid);
		  
		 
		  
//			login part started ----------------------------------------------------------------------------------------------------------------------------------------------------
			
			
		  if(custopt.isEmpty()) throw new CustomerException("Customer not found with id :"+cid);
			
			
			
			CurrentUserSession RunningSession =	currentuser.findByUuid(key);
			
			if(RunningSession ==null)
			{
				throw new LoginException("Please provide your valid Unique Login key ?");
			}
			if(RunningSession.getUserId()!=custopt.get().getCustomerId())
			{
				throw new LoginException("please do Login first......");
			}
//			login part ended ----------------------------------------------------------------------------------------------------------------------------------------------------
			
		  Product product =prodopt.get();
		  
		  Customer customer =custopt.get();
		  
		Cart cust_cart =  customer.getCart();
		cust_cart.setProduct(product);
		customer.setCart(cust_cart);
		custDao.save(customer);
		  
		
		return product.toString()+"This product were added into your cart...";
	}







	@Override
	public List<Product> viewAllProductsFromCart(Integer cid, String key) throws CustomerException, LoginException {
		  Optional<Customer>custopt =  custDao.findById(cid);
		  
//			login part started ----------------------------------------------------------------------------------------------------------------------------------------------------
			
		  if(custopt.isEmpty()) throw new CustomerException("Customer not found with id :"+cid);
			
			CurrentUserSession RunningSession =	currentuser.findByUuid(key);
			
			if(RunningSession ==null)
			{
				throw new LoginException("Please provide your valid Unique Login key ?");
			}
			if(RunningSession.getUserId()!=custopt.get().getCustomerId())
			{
				throw new LoginException("please do Login first......");
			}
//			login part ended ----------------------------------------------------------------------------------------------------------------------------------------------------
	
			Cart cart=null;
			if(custopt.isPresent())
			{
			    Customer customer =	custopt.get();
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

}
