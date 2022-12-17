//package com.onestore.controller;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.onestore.exception.CartException;
//import com.onestore.exception.CustomerException;
//import com.onestore.exception.LoginException;
//import com.onestore.model.Order;
//import com.onestore.service.OrderService;
//
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@RestController
//public class OrderController {
//	@Autowired
//	OrderService orderService;
//	
//	@PostMapping("/order/{key}")
//	public ResponseEntity<Order> addOrder(@RequestBody Order order,@PathVariable("key") String key) throws LoginException, CustomerException, CartException{
//		
//		Order new_order = orderService.addOrder(order, key);
//		return new ResponseEntity<Order>(new_order,HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/order/{key}")
//	public ResponseEntity<Order> updateOrder(@RequestBody Order updatedOrder,@PathVariable("key") String key) throws LoginException, CustomerException{
//		Order orderUpdate = orderService.updateOrder(updatedOrder, key);
//		return new ResponseEntity<Order>(orderUpdate,HttpStatus.ACCEPTED);
//	}
//	
//	@DeleteMapping("/order/{orderId}/{key}")
//	public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") Integer orderId,@PathVariable("key") String key) throws LoginException, CustomerException{
//		Order deletedOrder = orderService.removeOrder(orderId, key);
//		return new ResponseEntity<Order>(deletedOrder,HttpStatus.OK);
//	}
//	
//	@GetMapping("/order/{orderId}")
//	public ResponseEntity<Order> viewOrder(@PathVariable("orderId") Integer orderId){
//		Order order = orderService.viewOrder(orderId);
//		return new ResponseEntity<Order>(order,HttpStatus.OK);
//		
//	}
//	
//	
//	@GetMapping("/order/date/{date}")
//	public ResponseEntity<List<Order>> viewOrderByDate(@PathVariable("date") LocalDate date){
//			List<Order> orderByDate = orderService.viewAllOrderByDate(date);
//		
//		return new ResponseEntity<>(orderByDate,HttpStatus.ACCEPTED);
//	}
//	
//	@GetMapping("/order/{id}")
//	public ResponseEntity<List<Order>> viewOrderByUserid(@PathVariable("id") Integer userid ) throws CustomerException{
//		List<Order> orderList = orderService.viewAllOrderByUserId(userid); 
//		return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
//	}
//
//}
