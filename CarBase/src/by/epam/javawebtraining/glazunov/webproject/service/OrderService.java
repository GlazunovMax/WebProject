package by.epam.javawebtraining.glazunov.webproject.service;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.entity.Order;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public interface OrderService {
	
	/**
	 * Get all the orders owned by the specific client.
	 *  
	 * @param id - client's id
	 * @throws ServiceException - if can't get all the orders owned by the specific client
	 */
	List<Order> getOrderById(Long id, int offset, int countRows) throws ServiceException;

	/**
	 * Get all orders.
	 * 
	 * @return List of all orders
	 * @throws ServiceException if can't get all orders
	 */
	//List<Order> getAllOrder() throws ServiceException;

	
	/**
	 * Get all orders which is not in the route ...(driver not assigned and not in Routes table)
	 * 
	 * @return List of all orders which is not in the route
	 * @throws ServiceException if can't get all orders which is not in the route
	 */
	List<Order> getAllOrderWithoutRoute(int offset, int countRows) throws ServiceException;

	/**
	 * Get order by id.
	 * 
	 * @return order
	 * @param id - the order's id
	 * @throws ServiceException if can't get order by id.
	 */
	Order getSingleOrderById(long id) throws ServiceException;

	/**
	 * Add the order.
	 * 
	 * @param order - the order
	 * @throws ServiceException if can't add order 
	 */
	void addOrder(Order order) throws ServiceException;

	/**
	 * Delete the order by id.
	 * 
	 *  @param id - the order's id
	 *  @throws ServiceException if can't delete order
	 */
	void removeOrder(long id) throws ServiceException;
	
	/**
	 * update the order.
	 * 
	 * @param order - the order
	 * @throws ServiceException if can't update order 
	 */
	void updateOrder(Order order) throws ServiceException;
}
