package by.epam.javawebtraining.glazunov.webproject.entity;

import java.io.Serializable;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public class Model implements Serializable {
	private static final long serialVersionUID = -5435237949784608534L;

	/** Field id */
	private long id;

	/**
	 * Creates a new object
	 * 
	 * @see Model#Model(long)
	 */
	public Model() {
	}

	/**
	 * Creates a new object
	 * 
	 * @see Model#Model()
	 * @param id - object id
	 */
	public Model(long id) {
		this.id = id;
	}

	/**
	 * Method of obtaining the value of the field {@link Model#id}
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Method of setting the value of the field {@link Model#id}
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id= " + id;
	}

}
