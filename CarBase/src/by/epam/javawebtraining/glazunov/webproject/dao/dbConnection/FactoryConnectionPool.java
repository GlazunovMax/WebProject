package by.epam.javawebtraining.glazunov.webproject.dao.dbConnection;

public class FactoryConnectionPool {

	private static final FactoryConnectionPool instance = new FactoryConnectionPool();
	
	private ConnectionPool pool = new ConnectionPool();

	

	private FactoryConnectionPool() {}

	public static FactoryConnectionPool getInstance() {
		return instance;
	}
	
	public ConnectionPool getConnectionPool() {
		return pool;
	}
	
	
}
