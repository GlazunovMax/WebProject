package by.epam.javawebtraining.glazunov.webproject.service;

import java.util.List;
import by.epam.javawebtraining.glazunov.webproject.entity.Feedback;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

public interface FeedbackService {
	
	/**
	 * Add the feedback.
	 * 
	 * @param feedback - the feedback
	 * @throws ServiceException if can't add feedback.
	 */
	void addFeedBack(Feedback feedback) throws ServiceException;
	
	/**
	 * Delete the feedback by id.
	 * 
	 * @param id - the feedback's id
	 * @throws ServiceException if can't delete feedback by id.
	 */
	void removeFeedBack(long id) throws ServiceException;
		
	/**
	 * Get all feedback.
	 * 
	 * @return List of all feedback
	 * @throws ServiceException if can't get all feedback
	 */
	List<Feedback> getAllFeedback(int offset, int countRows) throws ServiceException;
		
	/**
	 * Get all the feedback owned by the specific client.
	 *  
	 * @param id - client's id
	 * @throws ServiceException - if can't get all the feedback owned by the specific client
	 */
	List<Feedback> getAllFeedbackByClientId(long id, int offset, int countRows) throws ServiceException;
		
	/**
	 * Change feedback.
	 * @param feedback - feedback
	 * 
	 * @throws ServiceException - if can't change feedback
	 */
	void editFeedback(Feedback feedback) throws ServiceException;
	
	
	/**
	 * Get feedback by id.
	 *  
	 * @param id - feedback's id
	 * @throws ServiceException - if can't get the feedback by id
	 */
	Feedback getFeedbackById(long id) throws ServiceException;
	
}
