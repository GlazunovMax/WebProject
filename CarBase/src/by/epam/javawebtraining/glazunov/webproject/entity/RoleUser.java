package by.epam.javawebtraining.glazunov.webproject.entity;

import java.util.HashSet;
import java.util.Set;

public class RoleUser extends Model {
	private static final long serialVersionUID = -8976683459669638393L;
	
	private String titleRole;
	private Set<User> users;

	
	/**
	 * Creates a new object
	 * 
	 * @see RoleUser#RoleUser(long)
	 */
	public RoleUser() {
		super();
		users = new HashSet<>();
	}

	/**
	 * Creates a new object
	 * @see RoleUser#RoleUser()
	 * @param id
	 */
	public RoleUser(long id) {
		super(id);
	}
	
	/**
	 * Method of obtaining the value of the field {@link RoleUser#users}
	 * @return users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * Method of setting the value of the field {@link RoleUser#users}
	 * @param users
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * Method of obtaining the value of the field {@link RoleUser#titleRole}
	 * @return titleRole
	 */
	public String getTitleRole() {
		return titleRole;
	}

	/**
	 * Method of setting the value of the field {@link RoleUser#titleRole}
	 * @param titleRole
	 */
	public void setTitleRole(String titleRole) {
		this.titleRole = titleRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((titleRole == null) ? 0 : titleRole.hashCode());
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
		RoleUser other = (RoleUser) obj;
		if (titleRole == null) {
			if (other.titleRole != null)
				return false;
		} else if (!titleRole.equals(other.titleRole))
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
		return "Role [titleRole=" + titleRole + ", users=" + users + "]";
	}

}
