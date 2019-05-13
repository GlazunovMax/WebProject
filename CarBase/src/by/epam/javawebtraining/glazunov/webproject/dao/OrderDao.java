package by.epam.javawebtraining.glazunov.webproject.dao;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Order;

/**
 * @author Glazunov
 * @version 1.0
 */
public interface OrderDao extends ItemDao<Order> {

	/**
	 * Get all the orders owned by the specific client.
	 *  
	 *  @param id - client's id
	 *  @throws DaoException - if can't get all the orders owned by the specific client
	 */
	List<Order> getOrderById(Long id, int offset, int countRows) throws DaoException;

	/**
	 * Get all orders from the DB which is not in the route ...(driver not assigned and not in Routes table)
	 * 
	 * @return List of all orders which is not in the route
	 * @throws DaoException if can't get all orders which is not in the route
	 */
	List<Order> getAllOrderWithoutRoute(int offset, int countRows) throws DaoException;

	/**
	 * Get order from the DB by id.
	 * 
	 * @return order
	 * @param id - the order's id
	 * @throws DaoException if can't get order from the DB by id.
	 */
	Order getSingleOrderById(long id) throws DaoException;
	
	/**
	 * Update order in the DB.
	 * 
	 * @param order - the order
	 * @throws DaoException if can't update order in the DB.
	 */
	void updateOrder(Order order) throws DaoException;
 
}
