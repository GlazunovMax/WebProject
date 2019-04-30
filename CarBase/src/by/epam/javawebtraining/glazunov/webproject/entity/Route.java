package by.epam.javawebtraining.glazunov.webproject.entity;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public class Route extends Model {
	private static final long serialVersionUID = -5269173767031313573L;
	
	private Order order;
	private User driver;
	private Mark mark;
	
	/**
	 * Creates a new object
	 * 
	 * @see Route#Route(long)
	 */
	public Route() {
		super();
	}
	
	/**
	 * Creates a new object
	 * @see Route#Route()
	 * @param id
	 */
	public Route(long id) {
		super(id);
	}
	
	/**
	 * Method of obtaining the value of the field {@link Route#order}
	 * @return order
	 */
	public Order getOrder() {
		return order;
	}
	
	
	/**
	 * Method of setting the value of the field {@link Route#order}
	 * @param order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	
	/**
	 * Method of obtaining the value of the field {@link Route#driver}
	 * @return driver(user)
	 */
	public User getDriver() {
		return driver;
	}
	
	/**
	 * Method of setting the value of the field {@link Route#driver}
	 * @param driver
	 */
	public void setDriver(User driver) {
		this.driver = driver;
	}
	
	/**
	 * Method of obtaining the value of the field {@link Route#mark}
	 * @return mark
	 */
	public Mark getMark() {
		return mark;
	}
	
	/**
	 * Method of setting the value of the field {@link Route#mark}
	 * @param mark
	 */
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		Route other = (Route) obj;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!driver.equals(other.driver))
			return false;
		if (mark != other.mark)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Route [order=" + order + ", driver=" + driver + ", mark=" + mark + "]";
	}
	
	/**
	 * 
	 * @author Glazunov
	 * The enum mark - car flight completed or not
	 */
	public enum Mark {
		DONE, NOT_DONE;
	}
	
}
