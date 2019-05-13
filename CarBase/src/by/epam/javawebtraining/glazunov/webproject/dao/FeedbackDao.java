package by.epam.javawebtraining.glazunov.webproject.dao;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Feedback;

public interface FeedbackDao {

	void addFeedBack(Feedback feedback) throws DaoException;
	
	void removeFeedBack(long id) throws DaoException;
	
	List<Feedback> getAllFeedback() throws DaoException;
	
	List<Feedback> getAllFeedbackByClientId(long id) throws DaoException;
	
	void editFeedback(Feedback feedback) throws DaoException;
	
	Feedback getFeedbackById(long id) throws DaoException;
	
}
