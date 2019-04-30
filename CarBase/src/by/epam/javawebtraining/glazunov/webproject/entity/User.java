package by.epam.javawebtraining.glazunov.webproject.entity;

import java.util.List;
import java.util.Set;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public class User extends Model {
	private static final long serialVersionUID = -6566261295494264729L;

	private String name;
	private String surname;
	private String login;
	private String password;
	private String phoneNumber;
	private Role role;
	
	private Set<Car> cars;
	
	private List<Order> orders;
	
	private List<Route> routes;
	
	/**
	 * Creates a new object
	 * 
	 * @see User#User(long)
	 */
	public User() {
		super();
	}

	/**
	 * Creates a new object
	 * @see User#User()
	 * @param id
	 */
	public User(long id) {
		super(id);
	}

	/**
	 * Method of obtaining the value of the field {@link User#name}
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method of setting the value of the field {@link User#name}
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method of obtaining the value of the field {@link User#surname}
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Method of setting the value of the field {@link User#surname}
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Method of obtaining the value of the field {@link User#login}
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Method of setting the value of the field {@link User#login}
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Method of obtaining the value of the field {@link User#password}
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method of setting the value of the field {@link User#password}
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Method of obtaining the value of the field {@link User#phoneNumber}
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Method of setting the value of the field {@link User#phoneNumber}
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Method of setting the value of the field {@link User#role}
	 * @return role
	 */
	public Role getRole() {
		return role;
	}
	
	/**
	 * Method of setting the value of the field {@link User#role}
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Method of setting the value of the field {@link User#cars}
	 * @return set cars
	 */
	public Set<Car> getCars() {
		return cars;
	}

	/**
	 * Method of setting the value of the field {@link User#cars}
	 * @param cars
	 */
	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	/**
	 * Method of setting the value of the field {@link User#orders}
	 * @return set orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * Method of setting the value of the field {@link User#orders}
	 * @param orders
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * Method of setting the value of the field {@link User#routes}
	 * @return set routes
	 */
	public List<Route> getRoutes() {
		return routes;
	}

	
	/**
	 * Method of setting the value of the field {@link User#routes}
	 * @param routes
	 */
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "User [" + super.toString() + " name= " + name + ", surname= " + surname + ", login= " + login + ", password= " + password
				+ ", phoneNumber=" + phoneNumber + ", role=" + role + ", cars=" + cars + ", orders=" + orders
				+ ", routes=" + routes + "]";
	}

	/**
	 * 
	 * @author Glazunov
	 * The enum role - role users
	 */
	public enum Role {
		DISPATCHER, CLIENT, DRIVER;
	}
}
