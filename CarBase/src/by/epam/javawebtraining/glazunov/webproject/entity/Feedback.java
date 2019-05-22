package by.epam.javawebtraining.glazunov.webproject.entity;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public class Feedback extends Model{
	private static final long serialVersionUID = 6538852565281520092L;
	
	private String text;
	private User user;
	
	/**
	 * Creates a new object
	 * 
	 * @see Feedback#Feedback(long)
	 */
	public Feedback() {
		super();
	}

	/**
	 * Creates a new object
	 * @see Feedback#Feedback()
	 * @param id
	 */
	public Feedback(long id) {
		super(id);
	}

	/**
	 * Method of obtaining the value of the field {@link Feedback#text}
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Method of setting the value of the field {@link Feedback#text}
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Method of obtaining the value of the field {@link Feedback#user}
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Method of setting the value of the field {@link Feedback#user}
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Feedback [text=" + text + ", user=" + user + "]";
	}
	
}
