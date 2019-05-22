package by.epam.javawebtraining.glazunov.webproject.entity;

import java.util.Set;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public class Car extends Model {
	private static final long serialVersionUID = -2857233886639958058L;

	private String mark;
	private String number;
	private StatusCar statusCar;

	private Set<User> users;//drivers
	
	/**
	 * Creates a new object
	 * 
	 * @see Car#Car(long)
	 */
	public Car() {
		super();
	}

	/**
	 * Creates a new object
	 * @see Car#Car()
	 * @param id
	 */
	public Car(long id) {
		super(id);
	}

	/**
	 * Method of obtaining the value of the field {@link Car#mark}
	 * @return mark - car model
	 */
	public String getMark() {
		return mark;
	}

	/**
	 * Method of setting the value of the field {@link Car#mark}
	 * @param mark
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}

	/**
	 * Method of obtaining the value of the field {@link Car#number}
	 * @return number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Method of setting the value of the field {@link Car#number}
	 * @param number - car number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	
	/**
	 * Method of obtaining the value of the field {@link Car#statusCar}
	 * @return statusCar
	 */
	public StatusCar getStatusCar() {
		return statusCar;
	}

	/**
	 * Method of setting the value of the field {@link Car#statusCar}
	 * @param statusCar - car condition
	 */
	public void setStatusCar(StatusCar statusCar) {
		this.statusCar = statusCar;
	}

	/**
	 * Method of obtaining the value of the field {@link Car#users}
	 * @return users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * Method of setting the value of the field {@link Car#users}
	 * @param users  - users drive car(drivers)
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((statusCar == null) ? 0 : statusCar.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		Car other = (Car) obj;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (statusCar != other.statusCar)
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [mark=" + mark + ", number=" + number + ", condition=" + statusCar + ", users=" + users + "]";
	}
	
	/**
	 * 
	 * @author Glazunov
	 * The enum statusCar sets car condition
	 */
	public enum StatusCar {
		GOOD, BROKEN;
	}

}


