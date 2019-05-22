package by.epam.javawebtraining.glazunov.webproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.dao.OrderDao;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.dao.factory.DaoFactory;
import by.epam.javawebtraining.glazunov.webproject.entity.Order;
import by.epam.javawebtraining.glazunov.webproject.service.OrderService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

/**
 * The OrderService class implements business logic.
 * @author Glazunov
 * @version 1.0
 */
public class OrderServiceImpl implements OrderService {
	private DaoFactory factory = DaoFactory.getInstance();
	private OrderDao orderDao = factory.getOrderDao();

	
	/**
	 * Get all orders.
	 * 
	 * @return List of all orders
	 * @throws ServiceException if can't get all orders
	 */
	/*@Override
	public List<Order> getAllOrder() throws ServiceException {
		try {
			return orderDao.getAll();
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_ALL_ORDER, e);
		}
	}*/

	/**
	 * Get all orders which is not in the route ...(driver not assigned and not in Routes table)
	 * 
	 * @return List of all orders which is not in the route
	 * @throws ServiceException if can't get all orders which is not in the route
	 */
	@Override
	public List<Order> getAllOrderWithoutRoute(int offset, int countRows) throws ServiceException {
		try {
			return orderDao.getAllOrderWithoutRoute(offset, countRows);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_ALL_ORDER_WITHOUT_ROUTE, e);
		}	
	}
	
	/**
	 * Add the order.
	 * 
	 * @param order - the order
	 * @throws ServiceException if can't add order 
	 */
	@Override
	public void addOrder(Order order) throws ServiceException {
		Validation.validateModel(order);
		//Validation.validateOrder(order);
		try {
			orderDao.add(order);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_ADD_ORDER);
		}
	}

	/**
	 * Delete the order by id.
	 * 
	 *  @param id - the order's id
	 *  @throws ServiceException if can't delete order
	 */
	@Override
	public void removeOrder(long id) throws ServiceException {
	
		Validation.validId(id);
		try {
			orderDao.remove(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_REMOVE_ORDER, e);
		}

	}

	/**
	 * Get all the orders owned by the specific client.
	 *  
	 * @param id - client's id
	 * @throws ServiceException - if can't get all the orders owned by the specific client
	 */
	@Override
	public List<Order> getOrderById(Long id, int offset, int countRows) throws ServiceException {
		List<Order> orders = new ArrayList<>();
		
		Validation.validId(id);
		try {
			orders = orderDao.getOrderById(id, offset, countRows);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_ORDERS_BY_ID, e);
		}	
		return orders;
	}

	/**
	 * Get order by id.
	 * 
	 * @return order
	 * @param id - the order's id
	 * @throws ServiceException if can't get order by id.
	 */
	@Override
	public Order getSingleOrderById(long id) throws ServiceException {
		Order order = new Order();
		
		Validation.validId(id);
		
		try {
			order = orderDao.getSingleOrderById(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_ORDER_BY_ID, e);
		}
		return order;
	}

	@Override
	public void updateOrder(Order order) throws ServiceException {
		Validation.validateModel(order);
		
		try {
			orderDao.updateOrder(order);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_UPDATE_ORDER);
		}
	}

	

}
