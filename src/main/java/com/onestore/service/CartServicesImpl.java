package com.onestore.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.model.Cart;
import com.onestore.model.CurrentUserSession;
import com.onestore.model.Customer;
import com.onestore.model.Product;
import com.onestore.repository.CustomerDao;
import com.onestore.repository.ProductDao;
import com.onestore.repository.UserSessionDao;

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
	public Product updateProductQuantity(Integer cid, Integer pid, Integer quantity, String key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addProductToCart(Integer pid, Integer custId, String key) throws CustomerException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

}
