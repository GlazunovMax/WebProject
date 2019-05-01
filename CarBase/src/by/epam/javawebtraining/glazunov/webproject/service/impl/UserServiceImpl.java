package by.epam.javawebtraining.glazunov.webproject.service.impl;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.dao.UserDao;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.dao.factory.DaoFactory;
import by.epam.javawebtraining.glazunov.webproject.entity.User;
import by.epam.javawebtraining.glazunov.webproject.service.UserService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

/**
 * The UserService class implements business logic.
 * @author Glazunov
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
	private DaoFactory factory = DaoFactory.getInstance();
	private UserDao userDao = factory.getUserDao();

	
	/** Check user by login and password
	 * 
	 * @param login
	 * @param password
	 * @return returns user
	 * @throws ServiceException if you cannot to check user by login and password 
	 */
	@Override
	public User singIn(String login, String password) throws ServiceException {
		User user = null;

		Validation.validateSingIn(login, password);
		try {
			user = userDao.singIn(login, password);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_LOGIN_OR_PASSWORD ,e);
		}
		
		return user;
	}

	/** Add the user.
	 * 
	 * @param user
	 * @throws ServiceException if you cannot add user
	 */
	@Override
	public void registration(User user) throws ServiceException {
		//Validation.validateRegistration(user);
		Validation.validateModel(user);
		
		try {
			userDao.registration(user);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_DATA_USER, e);
		}
		
	}

	/**
	 * Get all drivers.(user table)
	 * 
	 * @return List of all drivers
	 * @throws ServiceException if can't get all drivers
	 */
	@Override
	public List<User> getAllDriver() throws ServiceException {
		try {
			return userDao.getAllDriver();
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_ALL_DRIVERS, e);
		}
	}

	/**
	 * Get user by id.
	 *  
	 * @param id - user's id
	 * @throws ServiceException - if can't get user by id
	 */
	@Override
	public User getUserById(Long id) throws ServiceException {
		User user = null;
		
		Validation.validId(id);
		
		try {
			user = userDao.getUserById(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_USER_BY_ID, e);
		}
		
		return user;
	}

}
