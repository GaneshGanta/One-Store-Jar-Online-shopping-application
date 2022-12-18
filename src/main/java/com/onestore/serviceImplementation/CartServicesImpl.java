package com.onestore.serviceImplementation;

import java.lang.StackWalker.Option;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public ProductDto removeproductFromCart(Integer pid, String key,Integer quantity) throws CustomerException, LoginException{
		  
		Customer customer = valid.validateLogin(key);
		

		if(customer==null)
		{
			
			
			Cart customer_cart =customer.getCart();
			
			List<ProductDto> productList=  customer_cart.getProducts();
			
			
			
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
			
			
			Optional<ProductDto> optp = productDao.findById(pid);
			if(optp.isPresent())
			{
				ProductDto product =optp.get();
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
		
<<<<<<< HEAD
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
=======
>>>>>>> 0a2add6a021d60853159288bc871cc1cdb7438cf
		
		throw new CustomerException("Invalid customerId:"+customer.getCustomerId());
	}
	
	
	
	
	
	

	@Override

	public ProductDto updateProductQuantity(Integer pDtoId, Integer quantity, String key) throws CustomerException,LoginException {
		
		
		Customer customer = valid.validateLogin(key);
		
		
			
			 
			 Cart cart_cus= customer.getCart();
			 List<ProductDto> li= cart_cus.getProducts();
			 
				boolean flag=false;
				
				ProductDto p=null;
				
				 for(int i=0;i<li.size();i++){
					 if(li.get(i).getProductId()==pDtoId) {
						 li.get(i).setQuantity(li.get(i).getQuantity()+quantity);
						 p=li.get(i);
						 flag=true;
						 break;
					 }
				 }
				 if(!flag) throw new CustomerException("Product Not found");
				 
				 cart_cus.setProducts(li);
				 customer.setCart(cart_cus);
				 custDao.save(customer);
		 
				 return p;
		 
		 
		 
        
//				Customer customer = valid.validateLogin(key);
//				if(customer==null)throw new CustomerException("customer not found with uuid:"+key);
//				
//				List<ProductDto> productDtolist = customer.getCart().getProducts();
//				
//				
//				System.out.println(productDtolist+"--------------------");
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
//			     Cart customerCart = customer.getCart();
//			      customerCart.setProducts(productDtolist);
//			
//			       customer.setCart(customerCart);
//			       custDao.save(customer);
//			       return product;
			}
		
			
			
		
	

	
		 
	

	@Override
	public Cart addProductToCart(Integer pid,  String key) throws CustomerException, LoginException, ProductException {
		    
		Optional<ProductDto> prodopt =productDao.findById(pid);
		
		Customer customer = valid.validateLogin(key);
		if(prodopt.isEmpty()) {
			throw new ProductException("Product Not Available!");
		}
		ProductDto product =prodopt.get();
		  
		Cart cust_cart =  customer.getCart();
		System.out.println(cust_cart+" "+cust_cart.getProducts().size());
		cust_cart.getProducts().add(product);
		Cart updatedCart = cartD.save(cust_cart);
		System.out.println(updatedCart.getProducts().size());
		customer.setCart(updatedCart);
		
		return updatedCart;
	}



	@Override
	public List<ProductDto> viewAllProductsFromCart(String key) throws CustomerException, LoginException, ProductException {
		Customer customer = valid.validateLogin(key);
		  
	
<<<<<<< HEAD
			
			
			List<ProductDto> products = productDao.findAll();
			
			if(products.isEmpty()) {
				
				throw new ProductException("empty list of products");
=======
			Cart cart=null;
			if(customer==null)
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
>>>>>>> 0a2add6a021d60853159288bc871cc1cdb7438cf
			}
			
			return products;
			
		
	}

<<<<<<< HEAD

	@Override
	public double cartTotal(String key)throws CustomerException, LoginException, ProductException{
		
		Customer customer = valid.validateLogin(key);
		
		
//		 
//		
		double price = 0;
//		
//		
		
		
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

=======
>>>>>>> 0a2add6a021d60853159288bc871cc1cdb7438cf
}
