package by.epam.javawebtraining.glazunov.webproject.entity;

import java.time.LocalDateTime;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public class Order extends Model {
	private static final long serialVersionUID = -922896197661683536L;
	
	private City departure;
	private City destination;
	private LocalDateTime timeDeparture;
	private int countPassenger;
	
	private User user;
	
	private Route route;
	
	/**
	 * Creates a new object
	 * 
	 * @see Order#Order(long)
	 */
	public Order() {
		super();
	}

	/**
	 * Creates a new object
	 * @see Order#Order()
	 * @param id
	 */
	public Order(long id) {
		super(id);
	}

	/**
	 * Method of obtaining the value of the field {@link Order#departure}
	 * @return departure
	 */
	public City getDeparture() {
		return departure;
	}

	/**
	 * Method of setting the value of the field {@link Order#departure}
	 * @param departure
	 */
	public void setDeparture(City departure) {
		this.departure = departure;
	}

	/**
	 * Method of obtaining the value of the field {@link Order#destination}
	 * @return destination
	 */
	public City getDestination() {
		return destination;
	}

	/**
	 * Method of setting the value of the field {@link Order#destination}
	 * @param destination
	 */
	public void setDestination(City destination) {
		this.destination = destination;
	}

	/**
	 * Method of obtaining the value of the field {@link Order#timeDeparture}
	 * @return timeDeparture
	 */
	public LocalDateTime getTimeDeparture() {
		return timeDeparture;
	}

	/**
	 * Method of setting the value of the field {@link Order#timeDeparture}
	 * @param timeDeparture
	 */
	public void setTimeDeparture(LocalDateTime timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	/**
	 * Method of obtaining the value of the field {@link Order#countPassenger}
	 * @return coutPassenger
	 */
	public int getCountPassenger() {
		return countPassenger;
	}

	/**
	 * Method of setting the value of the field {@link Order#countPassenger}
	 * @param countPassenger
	 */
	public void setCountPassenger(int countPassenger) {
		this.countPassenger = countPassenger;
	}

	
	/**
	 * Method of obtaining the value of the field {@link Order#user}
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Method of setting the value of the field {@link Order#user}
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Method of obtaining the value of the field {@link Order#route}
	 * @return route
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * Method of setting the value of the field {@link Order#route}
	 * @param route
	 */
	public void setRoute(Route route) {
		this.route = route;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + countPassenger;
		result = prime * result + ((departure == null) ? 0 : departure.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((route == null) ? 0 : route.hashCode());
		result = prime * result + ((timeDeparture == null) ? 0 : timeDeparture.hashCode());
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
		Order other = (Order) obj;
		if (countPassenger != other.countPassenger)
			return false;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		if (timeDeparture == null) {
			if (other.timeDeparture != null)
				return false;
		} else if (!timeDeparture.equals(other.timeDeparture))
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
		return "Order [" + super.toString() + 
				" departure= " + departure.getCityName() + 
				", destination= " + destination.getCityName() + 
				", timeDeparture= " + timeDeparture +
				", countPassenger= " + countPassenger + 
				", user= Name = " + user.getName() + " phone = " + user.getPhoneNumber() + 
				", route= " + route + "]";
	}	
	
}
