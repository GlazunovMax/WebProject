package by.epam.javawebtraining.glazunov.webproject.service.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.dao.FeedbackDao;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.dao.factory.DaoFactory;
import by.epam.javawebtraining.glazunov.webproject.entity.Feedback;
import by.epam.javawebtraining.glazunov.webproject.service.FeedbackService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

public class FeedbackServiceImpl implements FeedbackService {
	private DaoFactory factory = DaoFactory.getInstance();
	private FeedbackDao feedbackDao = factory.getFeedbackDao();
	
	/**
	 * Add the feedback.
	 * 
	 * @param feedback - the feedback
	 * @throws ServiceException if can't add feedback.
	 */
	@Override
	public void addFeedBack(Feedback feedback) throws ServiceException {
		Validation.validateModel(feedback);
		
		try {
			feedbackDao.add(feedback);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_ADD_FEEDBACK);
		}
		
	}

	/**
	 * Delete the feedback by id.
	 * 
	 * @param id - the feedback's id
	 * @throws ServiceException if can't delete feedback by id.
	 */
	@Override
	public void removeFeedBack(long id) throws ServiceException {
		Validation.validId(id);
		
		try {
			feedbackDao.remove(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_REMOVE_FEEDBACK, e);
		}
		
	}

	/**
	 * Get all feedback.
	 * 
	 * @return List of all feedback
	 * @throws ServiceException if can't get all feedback
	 */
	@Override
	public List<Feedback> getAllFeedback(int offset, int countRows) throws ServiceException {	
		try {
			return feedbackDao.getAllFeedback(offset, countRows);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_ALL_FEEDBACK, e);
		}
	}

	/**
	 * Get all the feedback owned by the specific client.
	 *  
	 * @param id - client's id
	 * @throws DaoException - if can't get all the feedbacks owned by the specific client
	 */
	@Override
	public List<Feedback> getAllFeedbackByClientId(long id, int offset, int countRows) throws ServiceException {
		Validation.validId(id);
		
		try {
			return feedbackDao.getAllFeedbackByClientId(id, offset, countRows);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_FEEDBACKS_BY_ID_DRIVER, e);
		}
	}

	/**
	 * Change feedback.
	 * @param feedback - feedback
	 * 
	 * @throws ServiceException - if can't change feedback
	 */
	@Override
	public void editFeedback(Feedback feedback) throws ServiceException {
		Validation.validateModel(feedback);
		
		try {
			feedbackDao.editFeedback(feedback);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_EDIT_FEEDBACK, e);
		}
	}

	@Override
	public Feedback getFeedbackById(long id) throws ServiceException {
		Validation.validId(id);
		try {
			return feedbackDao.getFeedbackById(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_FEEDBACK_BY_ID, e);
		}
	}

}
