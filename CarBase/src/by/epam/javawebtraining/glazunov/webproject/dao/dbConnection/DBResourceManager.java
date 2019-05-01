package by.epam.javawebtraining.glazunov.webproject.dao.dbConnection;

import java.util.ResourceBundle;

public class DBResourceManager {
	private static final String DB = "db";
	
	private final static DBResourceManager instance = new DBResourceManager();
	
	private ResourceBundle bundle = ResourceBundle.getBundle(DB);

	public static DBResourceManager getInstance() {
		return instance;
	} 

	public String getValue(String key){
		return bundle.getString(key);
	}
}
