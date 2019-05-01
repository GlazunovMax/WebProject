package by.epam.javawebtraining.glazunov.webproject.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPoolException;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.FactoryConnectionPool;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_CONNECTION_POOL_OK = "OK! Connecting to connection pool is SUCCESSFUL!";
	private static final String MESSAGE_ERROR_CONNECTION_POOL = "ERROR! Connecting to connection pool is FAILED!";
	private static final String MESSAGE_POOL_CLOSE = "Connection pool is closed!!";
	
	private static CommandProvider provider = new CommandProvider();
	private static Logger LOGGER = Logger.getLogger(Controller.class);

    public Controller() {
        super();
    }
    
    /**
	 * The method initializes the connection pool
	 */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
    	ConnectionPool connectionPool = factory.getConnectionPool();
    	
    	try {
			connectionPool.initPoolData();
			LOGGER.debug(MESSAGE_CONNECTION_POOL_OK);
		} catch (ConnectionPoolException e) {
			LOGGER.debug(MESSAGE_ERROR_CONNECTION_POOL);
			throw new ConnectionPoolError(MESSAGE_ERROR_CONNECTION_POOL, e);
		}
    }
    
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
    	super.service(arg0, arg1);
    }

    protected void processCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String cmd = request.getParameter("command");
		System.out.println(cmd);
		Command command = provider.getCommand(cmd);
		command.execute(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processCommand(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processCommand(request, response);
	}

	
	/**
	 * The method destroy connectionPool
	 */
	@Override
	public void destroy() {
		super.destroy();
		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();
		connectionPool.dispose();
		LOGGER.debug(MESSAGE_POOL_CLOSE);
	}
}
