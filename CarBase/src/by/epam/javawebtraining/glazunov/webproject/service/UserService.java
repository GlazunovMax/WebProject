package by.epam.javawebtraining.glazunov.webproject.service;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.entity.User;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

/**
 * 
 * @author Glazunov
 *
 */
public interface UserService {
	
	/** Check user by login and password
	 * 
	 * @param login
	 * @param password
	 * @return returns user
	 * @throws ServiceException if you cannot to check user by login and password 
	 */
	User singIn(String login, String password)throws ServiceException;

	/** Add the user.
	 * 
	 * @param user
	 * @throws ServiceException if you cannot add user
	 */
	void registration(User user)throws ServiceException;
	
	/**
	 * Get all drivers.(user table)
	 * 
	 * @return List of all drivers
	 * @throws ServiceException if can't get all drivers
	 */
	List<User> getAllDriver() throws ServiceException;
	
	/**
	 * Get user by id.
	 *  
	 * @param id - user's id
	 * @throws ServiceException - if can't get user by id
	 */
	User getUserById(Long id) throws ServiceException; 
}
