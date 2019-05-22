package by.epam.javawebtraining.glazunov.webproject.entity;

import java.util.List;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public class City extends Model {
	private static final long serialVersionUID = -7242345486333646911L;
	
	private String cityName;
	private List<Order> orders;
	
	/**
	 * Creates a new object
	 * 
	 * @see City#City(long)
	 */
	public City() {
		super();
	}
	
	/**
	 * Creates a new object
	 * @see City#City()
	 * @param id
	 */
	public City(long id) {
		super(id);
	}
	
	/**
	 * Method of obtaining the value of the field {@link City#cityName}
	 * @return cityName
	 */
	public String getCityName() {
		return cityName;
	}
	
	/**
	 * Method of setting the value of the field {@link City#cityName}
	 * @param cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	/**
	 * Method of obtaining the value of the field {@link City#orders}
	 * @return orders
	 */
	public List<Order> getOrders() {
		return orders;
	}
	
	/**
	 * Method of setting the value of the field {@link City#orders}
	 * @param orders
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
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
		City other = (City) obj;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [" + super.toString() +  " cityName = " + cityName + ", orders = " + orders + "]";
	}

}
