package by.epam.javawebtraining.glazunov.webproject.dao;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Feedback;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

/**
 * @author Glazunov
 * @version 1.0
 */
public interface FeedbackDao extends ItemDao<Feedback> {
	
	/**
	 * Get all Feedback.
	 * 
	 * @return List of all Feedback
	 * @throws DaoException if can't get all Feedback
	 */
	List<Feedback> getAllFeedback(int offset, int countRows) throws DaoException;
	
	/**
	 * Get all the Feedback owned by the specific client.
	 *  
	 * @param id - client's id
	 * @throws DaoException - if can't get all the Feedback owned by the specific client
	 */
	List<Feedback> getAllFeedbackByClientId(long id, int offset, int countRows) throws DaoException;
	
	/**
	 * Change feedback.
	 * @param feedback - feedback
	 * 
	 * @throws DaoException - if can't change feedback
	 */
	void editFeedback(Feedback feedback) throws DaoException;
		
	/**
	 * Get Feedback.
	 * 
	 * @return  Feedback
	 * @throws DaoException if can't get Feedback
	 */
	Feedback getFeedbackById(long id) throws DaoException;
	
}
