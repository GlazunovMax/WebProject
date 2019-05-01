package by.epam.javawebtraining.glazunov.webproject.dao;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.User;
 
/**
  * @author Glazunov
  * @version 1.0
  */
public interface UserDao {
	
	/** Check user by login and password to the database
	 * 
	 * @param login
	 * @param password
	 * @return returns user
	 * @throws DaoException if you cannot to check user by login and password 
	 */
	User singIn(String login, String password)throws DaoException;

	/** Add the user to the database
	 * 
	 * @param user
	 * @throws DaoException if you cannot add user
	 */
	void registration(User user)throws DaoException;
	
	/**
	 * Get all drivers from the DB.(user table)
	 * 
	 * @return List of all drivers
	 * @throws DaoException if can't get all drivers
	 */
	List<User> getAllDriver() throws DaoException;
	
	/**
	 * Get user by id.
	 *  
	 *  @param id - user's id
	 *  @throws DaoException - if can't get user by id
	 */
	User getUserById(Long id) throws DaoException;
}
