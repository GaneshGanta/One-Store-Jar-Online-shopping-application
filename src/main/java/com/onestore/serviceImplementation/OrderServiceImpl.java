package com.onestore.serviceImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.exception.OrderException;
import com.onestore.model.Customer;
import com.onestore.model.Order;
import com.onestore.model.Product;
import com.onestore.repository.CustomerDao;
import com.onestore.repository.OrderDao;
import com.onestore.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired	
	OrderDao orderRepo;
	
	@Autowired
	CustomerDao customerRepo;
	
	@Autowired
	Validation valid;
	

	@Override
	public Order addOrder(Order order, String key) throws LoginException, CustomerException {
		
		//validating customer and getting the customer object
		Customer customer = valid.validateLogin(key);
		
		//getting product list from cart and add it to order product list and empty cart
		List<Product> productList = customer.getCart().getProducts();
		if(productList.isEmpty()) {
			throw new OrderException("Your Cart is Empty! Please Add Products to your cart before Ordering");
		}else {
			order.setProductList(productList);
			order.setAddress(customer.getAddress());
			customer.getCart().setProducts(new ArrayList<>());
		}
		
		Order new_order = orderRepo.save(order);
		customer = customerRepo.save(customer);
		if(customer==null) {
			throw new CustomerException("Order: Error while emptying the cart!");
		}
		
		if(new_order!=null) {
			return order;
		}else {
			throw new OrderException("Error Creating Order!");
		}
		
		
	}

	@Override
	public Order updateOrder(Order orderUpdate, String key) throws LoginException, CustomerException {
		
		Customer customer = valid.validateLogin(key);
		
		Optional<Order> existingOrder = orderRepo.findById(orderUpdate.getOrderId());
		if(existingOrder.isPresent()) {
			Order order = existingOrder.get();
			order.setAddress(orderUpdate.getAddress());
			order.setOrderDate(orderUpdate.getOrderDate());
			order.setOrderStatus(orderUpdate.getOrderStatus());
			order.setProductList(orderUpdate.getProductList());
			order = orderRepo.save(order);
			
			return order;
		}else {
			throw new OrderException("Order is not available to update!");
		}

	}

	@Override
	public Order removeOrder(Integer orderID, String key) throws LoginException, CustomerException {
		//login validation
		Customer customer = valid.validateLogin(key);
	    List<Order> customer_order_list	 = customer.getOrder();
	    boolean flag = false;
		for(Order od : customer_order_list) {
			if(od.getOrderId()==orderID) {
				flag = true;
			}
		}
		if(flag)
		{
		Order deleted_order = orderRepo.findById(orderID).orElseThrow(()-> new OrderException("Order Not Found!"));
		orderRepo.delete(deleted_order);
		return deleted_order;
		}else {
			throw new OrderException("Current User Doesn't have this order with orderid "+orderID);
		}
		
		
		
		
	}

	@Override
	public Order viewOrder(Integer orderId) {

		Order order = orderRepo.findById(orderId).orElseThrow(()-> new OrderException("Order Not Availbale to view"));
		
		return order;
	}

	@Override
	public List<Order> viewAllOrderByDate(LocalDate date) {
		// TODO Auto-generated method stub
		List<Order> orderList = orderRepo.findByOrderDate(date);
		if(orderList.isEmpty()) {
			throw new OrderException("No Order for the mentioned date!");
		}else {
			return orderList;
		}
		
	}

	@Override
	public List<Order> viewAllOrderByLocation(String location) {
		// TODO Auto-generated method stub
		List<Order> orderList = orderRepo.findAll();
		if(orderList.isEmpty()){
			throw new OrderException("No Orders Available in Database!");
		}else {
			
			List<Order> newOrderList = new ArrayList<>();
			for(Order order: orderList) {
				if(order.getAddress().getCity()==location)
					newOrderList.add(order);
			}
			if(newOrderList.isEmpty()) {
				throw new OrderException("No Order for the mentioned location!");
			}else {
				return newOrderList;
			}
			
		}
		
	}

	@Override
	public List<Order> viewAllOrderByUserId(Integer userid) throws CustomerException {
		Optional<Customer> cust = customerRepo.findById(userid);
		if(cust.isPresent()) {
			Customer customer = cust.get();
			List<Order> orderList = customer.getOrder();
			return orderList;
		}else {
			throw new CustomerException("No user found!");
		}
		
	}

	
	

}
