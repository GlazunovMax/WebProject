package by.epam.javawebtraining.glazunov.webproject.dao.impl;

public class SomeConstant {

	//DATABASE_CAR_DAO //RESOURCE_CLOSE
	public static final String SQL_SELECT_ALL_CAR = "SELECT cars.id, cars.mark, cars.car_number, car_condition.status_car FROM cars INNER JOIN car_condition ON cars.car_condition_id = car_condition.id LIMIT ?,?";//WHERE car_condition.status_car = 'GOOD'
	public static final String SQL_TRANSACTION_VARIABLE = "SET @car_condition_id = 0";
	public static final String SQL_SELECT_TRANSACTION_CONDITION_CAR = "SELECT id INTO @car_condition_id FROM car_condition WHERE status_car = ?";
	public static final String SQL_INSERT_CAR_TRANSACTION = "INSERT INTO cars(mark, car_number, car_condition_id) VALUES(?, ?, @car_condition_id);";
	public static final String SQL_DELETE_UPDATE_CAR = "DELETE FROM cars WHERE id = ?";
	public static final String SQL_SELECT_DRIVERS = "SELECT users_has_cars.users_id, users_has_cars.cars_id, users.id, users.name, users.surname, users.login, users.password, users.phone, roles.title FROM users INNER JOIN users_has_cars ON users.id = users_has_cars.users_id  INNER JOIN cars ON cars.id = users_has_cars.cars_id INNER JOIN roles ON users.role_id = roles.id WHERE cars_id = ?";
	public static final String SQL_SELECT_CARS_BY_ID_DRIVER = "SELECT cars.id, cars.mark, cars.car_number, car_condition.status_car FROM users INNER JOIN users_has_cars ON users.id = users_has_cars.users_id INNER JOIN cars ON cars.id = users_has_cars.cars_id INNER JOIN car_condition ON cars.car_condition_id = car_condition.id WHERE users_has_cars.users_id = ? limit ?,?";
	public static final String SQL_EDIT_CAR_CONDITION = "UPDATE cars SET cars.car_condition_id = (SELECT car_condition.id FROM car_condition WHERE car_condition.status_car = ?) WHERE cars.id = ?";
	public static final String SQL_INSERT_CAR_WITH_DRIVERS = "INSERT INTO users_has_cars(users_id, cars_id) VALUES(?, (SELECT id FROM cars WHERE cars.mark = ?))";//"INSERT INTO users_has_cars(users_id, cars_id) VALUES(?, ?)";
	public static final String SQL_SELECT_CAR_BY_ID = "SELECT cars.id, cars.mark, cars.car_number, car_condition.status_car FROM cars INNER JOIN car_condition ON cars.car_condition_id = car_condition.id WHERE cars.id = ?";
	
	public static final String ID = "id";
	public static final String MARK = "mark";
	public static final String CAR_NUMBER = "car_number";
	public static final String CAR_CONDITION = "status_car";
	
	public static final String MESSAGE_RESULT_SET_CLOSED = "Result set is closed";
	public static final String MESSAGE_STATEMENT_CLOSED = "Statement is closed";
	public static final String MESSAGE_PREPARED_STATEMENT_CLOSED = "Prepared statement is closed";
	public static final String MESSAGE_CONNECTION_CLOSED = "Connection is closed";
	
	public static final String MESSAGE_GET_CAR_EXCEPTION = "Error! You cannot get all cars!";
	public static final String MESSAGE_CONNECTION_POOL_EXCEPTION = "Error connecting to the connection pool!";
	public static final String MESSAGE_RESULT_SET_CLOSED_EXCEPTION = "Warning!!! Result set is NOT closed";
	public static final String MESSAGE_STATEMENT_CLOSED_EXCEPTION = "Warning!!! Statement is NOT closed";
	public static final String ROLLBACK_EXCEPTION = "Rollback error!";
	public static final String ADD_CAR_EXCEPTION = "Error adding car!";
	public static final String PREPARED_STATEMENT_CLOSED_EXCEPTION = "Warning!!! Prepared statement is NOT closed";
	public static final String PREPARED_STATEMENT_INSERT_CLOSED_EXCEPTION = "Warning!!! Prepared statement insert is NOT closed";
	public static final String PREPARED_STATEMENT_CONDITION_CAR_CLOSED_EXCEPTION = "Warning!!! Prepared statement condition car is NOT closed";
	public static final String PREPARED_STATEMENT_VAR_CLOSED_EXCEPTION = "Warning!!! Prepared statement var is NOT closed";
	public static final String CONNECTION_CLOSED_EXCEPTION = "Warning!!! Connection is NOT closed";
	public static final String REMOVE_CAR_EXCEPTION = "Error remove car by id";
	public static final String GET_CARS_BY_ID_DRIVER_EXCEPTION = "Error! You cannot get car by id driver!";
	public static final String EDIT_CAR_CONDITION_EXCEPTION = "Error edit car condition!";
	public static final String ADD_CARS_FOR_DRIVER_EXCEPTION = "Error adding car for driver!";
	public static final String GET_CAR_BY_ID_EXCEPTION = "Error! You cannot get car by id!";
	
	
	//DatabaseCountRowsDao
	public static final String SQL_GET_ALL_CAR_BY_ID_DRIVER_COUNT = "SELECT COUNT(*) AS COUNT FROM users INNER JOIN users_has_cars ON users.id = users_has_cars.users_id INNER JOIN cars ON cars.id = users_has_cars.cars_id INNER JOIN car_condition ON cars.car_condition_id = car_condition.id WHERE users_has_cars.users_id = ?";
	public static final String COUNT = "COUNT";
	public static final String MESSAGE_FIND_COUNT_CAR_BY_ID_DRIVER_EXCEPTION = "Cannot find count cars by id driver";
	public static final String SQL_GET_ALL_ROUTE_BY_ID_DRIVER_COUNT = "SELECT COUNT(*) AS COUNT FROM routes INNER JOIN orders ON routes.orders_id = orders.id INNER JOIN users ON  orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users AS driv ON routes.users_id = driv.id INNER JOIN roles as rol ON driv.role_id = rol.id WHERE routes.users_id = ?";
	public static final String MESSAGE_FIND_COUNT_ROUTE_BY_ID_DRIVER_EXCEPTION = "Cannot find count routes by id driver";
	public static final String SQL_GET_ALL_ORDER_BY_ID_CLIENT = "SELECT COUNT(*) AS COUNT FROM orders INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users ON orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id WHERE users.id = ? ORDER BY orders.time_departure";
	public static final String MESSAGE_FIND_COUNT_ORDER_BY_ID_CLIENT_EXCEPTION = "Cannot find count orders by id client";
	public static final String SQL_GET_ALL_ORDER_WITHOUT_ROUTE = "SELECT COUNT(*) AS COUNT FROM orders INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users ON orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id WHERE orders.id NOT IN(SELECT routes.orders_id FROM routes)";
	public static final String MESSAGE_FIND_COUNT_ORDER_WITHOUT_ROUTE_EXCEPTION = "Cannot find count orders without route";
	public static final String SQL_GET_COUNT_ALL_ROUTE = "SELECT COUNT(*) AS COUNT FROM routes INNER JOIN orders ON routes.orders_id = orders.id INNER JOIN users ON  orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users AS driv ON routes.users_id = driv.id INNER JOIN roles as rol ON driv.role_id = rol.id";
	public static final String MESSAGE_FIND_COUNT_ROUTE_EXCEPTION = "Cannot find count all routes";
	public static final String SQL_GET_COUNT_ALL_CAR = "SELECT COUNT(*) AS COUNT FROM cars INNER JOIN car_condition ON cars.car_condition_id = car_condition.id";// WHERE car_condition.status_car = 'GOOD'
	public static final String MESSAGE_FIND_COUNT_CAR_EXCEPTION = "Cannot find count all cars";
	public static final String SQL_GET_ALL_FEEDBACK_BY_ID_CLIENT = "SELECT COUNT(*) AS COUNT FROM feedback WHERE feedback.user_id = ?";
	public static final String MESSAGE_FIND_COUNT_FEEDBACK_BY_ID_CLIENT_EXCEPTION = "Cannot find count all feedbacks by id client";
	public static final String SQL_GET_COUNT_ALL_FEEDBACK = "SELECT COUNT(*) AS COUNT FROM feedback";
	public static final String MESSAGE_FIND_COUNT_FEEDBACK_EXCEPTION = "Cannot find count all feedbacks";

	//DatabaseFeedbackDao
	public static final String SQL_TRANSACTION_VARIABLE_ADD_CLIENT_ID = "SET @client_id = 0";
	public static final String SQL_SELECT_TRANSACTION_CLIENT_ID = "SELECT id INTO @client_id FROM users WHERE surname = ?";
	public static final String SQL_INSERT_FEEDBACK_TRANSACTION = "INSERT INTO feedback(review, user_id) VALUES(?, @client_id)";
	public static final String ADD_FEEDBACK_EXCEPTION = "Error adding feedback!";
	public static final String SQL_REMOVE_FEEDBACK = "DELETE FROM feedback WHERE id = ?";
	public static final String REMOVE_FEEDBACK_EXCEPTION = "Error remove feedback by id!";
	public static final String SQL_SELECT_ALL_FEEDBACKS = "SELECT feedback.id, feedback.review, users.id AS client_id, users.name, users.surname, users.login, users.password, users.phone, roles.title FROM feedback INNER JOIN users ON feedback.user_id = users.id INNER JOIN roles ON roles.id = users.role_id LIMIT ?,?";
	public static final String GET_ALL_FEEDBACK_EXCEPTION = "Error! You cannot get all feedback!";
	public static final String SQL_SELECT_ALL_FEEDBACK_BY_ID = "SELECT feedback.id, feedback.review, users.id AS client_id, users.name, users.surname, users.login, users.password, users.phone, roles.title FROM feedback INNER JOIN users ON feedback.user_id = users.id INNER JOIN roles ON roles.id = users.role_id WHERE users.id = ? LIMIT ?,?";
	public static final String SQL_UPDATE_FEEDBACK_BY_ID = "UPDATE feedback SET feedback.review = ? WHERE feedback.id = ?";
	public static final String UPDATE_FEEDBACK_EXCEPTION = "Error! You cannot update feedback!";
	public static final String SQL_SELECT_FEEDBACK_BY_ID = "SELECT feedback.id, feedback.review, users.id AS client_id, users.name, users.surname, users.login, users.password, users.phone, roles.title FROM feedback INNER JOIN users ON feedback.user_id = users.id INNER JOIN roles ON roles.id = users.role_id WHERE feedback.id = ?";
	public static final String GET_FEEDBACK_EXCEPTION_BY_ID = "Error! You cannot get Feedback by id!";

	
	//CAR_SERVICE_IMPL
	public static final String MESSAGE_ERROR_GET_ALL_CAR = "Error!!! Can`t get all cars!";
	public static final String MESSAGE_ERROR_ADD_CAR = "Can`t add car!!!";
	public static final String MESSAGE_ERROR_REMOVE_CAR = "Error! Can`t remove car!";
	public static final String MESSAGE_ERROR_GET_CARS_BY_ID_DRIVER = "Error!!! Can`t get cars by id driver!";
	public static final String MESSAGE_EMPTY_CONDITION_EDIT_CAR_CONDITION = "Passed parameter car condition is empty!!";
	public static final String MESSAGE_ERROR_EDIT_CAR_CONDITION = "Error! Can`t edit car condition!";
	public static final String MESSAGE_ERROR_ADD_CAR_FOR_DRIVER = "Can`t add car for driver!!!";
	public static final String MESSAGE_ERROR_ADD_CARS_DRIVER = "Can`t add cars driver!!!";
	public static final String MESSAGE_ERROR_GET_CAR_BY_ID = "Error!!! Can`t get car by id!";
	
	//CountRowServiceImpl
	public static final String MESSAGE_ERROR_GET_COUNT_CAR_BY_ID_DRIVER = "Error!!! Can`t get count rows CARS by id driver!";
	public static final String MESSAGE_ERROR_GET_COUNT_ROUTES = "Error!!! Can`t get count rows ROUTES by id driver!";
	public static final String MESSAGE_ERROR_GET_COUNT_ORDERS_BY_ID_CLIENT = "Error!!! Can`t get count rows ORDERS by id client!";
	public static final String MESSAGE_ERROR_GET_COUNT_ALL_ORDER_WITHOUT_ROUTE = "Error!!! Can`t get count rows ORDERS without routes!";
	public static final String MESSAGE_ERROR_GET_COUNT_ROUTE = "Error!!! Can`t get count rows ROUTES!";
	public static final String MESSAGE_ERROR_GET_COUNT_CAR = "Error!!! Can`t get count rows CARS!";
	public static final String MESSAGE_ERROR_GET_COUNT_FEEDBACK = "Error!!! Can`t get count rows Feedback only client!";
	public static final String MESSAGE_ERROR_GET_COUNT_FEEDBAKS = "Error!!! Can`t get count rows all Feedback!";

	//FeedbackServiceImpl
	public static final String MESSAGE_ERROR_ADD_FEEDBACK = "Can`t add frrdback!!!";
	public static final String MESSAGE_ERROR_REMOVE_FEEDBACK = "Error! Can`t remove feedback!";
	public static final String MESSAGE_ERROR_GET_ALL_FEEDBACK = "Error!!! Can`t get all feedbacks!";
	public static final String MESSAGE_ERROR_GET_FEEDBACKS_BY_ID_DRIVER = "Error!!! Can`t get feedbacks by id client!";
	public static final String MESSAGE_ERROR_EDIT_FEEDBACK = "Error! Can`t edit feedback!";
	
	
	//DATABASE_CITY_DAO
	public static final String SQL_SELECT_ALL_CITIES = "SELECT id, name FROM city";
	public static final String SQL_INSERT_CITY = "INSERT INTO city (name) VALUES(?)";
	public static final String SQL_DELETE_UPDATE_CITY = "DELETE FROM city WHERE id = ?";
	public static final String SQL_SELECT_CITY_BY_ID = "SELECT id, name FROM city WHERE id = ?";
	
	public static final String NAME = "name";
	
	public static final String GET_ALL_CITIES_EXCEPTION = "Error! You cannot get all cities!";
	public static final String ADD_CITY_EXCEPTION = "Error! You cannot add city!";
	public static final String REMOVE_CITY_EXCEPTION = "Error remove city by id";
	public static final String GET_CITY_BY_ID_EXCEPTION = "Error! You cannot get city by id!";
	
	//CITY_SERVICE_IMPL
	public static final String MESSAGE_ERROR_GET_ALL_CITY = "Error!!! Can`t get all cities!";
	public static final String MESSAGE_ERROR_ADD_CITY = "Can`t add city!!!";
	public static final String MESSAGE_ERROR_REMOVE_CITY = "Error! Can`t remove city!";
	public static final String MESSAGE_ERROR_GET_CITY_BY_ID = "Error!!! Can`t get city by id!";
	
	
	
	//DATABASE_USER_DAO
	public static final String SQL_SELECT_SING_IN = "SELECT users.id, users.name, users.surname, users.login, users.password, users.phone, roles.title FROM users INNER JOIN roles ON users.role_id = roles.id WHERE users.login =? AND users.password =?";
	public static final String SQL_TRANSACTION_VARIABLE_REGISTRATION = "SET @role_id = 0";
	public static final String SQL_SELECT_TRANSACTION_ROLE = "SELECT id INTO @role_id FROM roles WHERE title =?";
	public static final String SQL_INSERT_USER_TRANSACTION = "INSERT INTO users(name, surname, login, password, phone, role_id) VALUES(?, ?, ?, ?, ?, @role_id)";
	public static final String SQL_SELECT_ALL_DRIVERS = "SELECT users.id, users.name, users.surname, users.login, users.password, users.phone, roles.title FROM users INNER JOIN roles ON users.role_id = roles.id WHERE roles.title = 'driver'";
	public static final String SQL_SELECT_ALL_ORDERS_WITHOUT_ROUTE = "SELECT orders.id, dep.id as id_city_dep, dep.name as city_name_dep, city.id as id_city_des, city.name as city_name_des, orders.time_departure, orders.count_passenger, users.id as user_id, users.name, users.surname, users.login, users.password, users.phone, roles.title FROM orders INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users ON orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id WHERE orders.id NOT IN(SELECT routes.orders_id FROM routes) LIMIT ?,?";
	public static final String SQL_SELECT_ORDER_BY_ID = "SELECT orders.id, dep.id as id_city_dep, dep.name as city_name_dep, city.id as id_city_des, city.name as city_name_des, orders.time_departure, orders.count_passenger, users.id as user_id, users.name, users.surname, users.login, users.password, users.phone, roles.title FROM orders INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users ON orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id WHERE orders.id = ?";
	public static final String SQL_SELECT_DRIVERS_CAR = "SELECT users_has_cars.users_id, users_has_cars.cars_id, cars.id, cars.mark, cars.car_number, car_condition.status_car FROM users INNER JOIN users_has_cars ON users.id = users_has_cars.users_id INNER JOIN cars ON cars.id = users_has_cars.cars_id INNER JOIN car_condition ON cars.car_condition_id = car_condition.id WHERE users_id = ?";
	public static final String SQL_SELECT_USER_BY_ID = "SELECT users.id, users.name, users.surname, users.login, users.password, users.phone, roles.title FROM users INNER JOIN roles ON users.role_id = roles.id WHERE users.id = ?";
												 
	public static final String SURNAME = "surname";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String TITLE = "title";
	public static final String PHONE = "phone";
	
	public static final String MESSAGE_USER_NOT_REGISTER = "User is not registered!";
	public static final String MESSAGE_SIGH_IN_EXCEPTION = "User cannot sing in!";
	public static final String MESSAGE_REGISTRATION_EXCEPTION = "User cannot registration";
	public static final String MESSAGE_GET_DRIVER_EXCEPTION = "Error! You cannot get all drivers!";
	public static final String GET_ORDER_EXCEPTION_BY_ID = "Error! You cannot get order by id!";
	public static final String GET_USER_BY_ID_EXCEPTION = "Error! You cannot get user by id!";
	
	public static final String SQL_UPDATE_ORDER_BY_ID = "UPDATE orders SET orders.city_id_departure=(SELECT id FROM city WHERE name = ?), orders.city_id_destination=(SELECT id FROM city WHERE name = ?), orders.time_departure = ?, orders.count_passenger=? WHERE orders.id = ?";
	public static final String UPDATE_ORDER_EXCEPTION = "Error! You cannot update order!";

	
	//USER_SERVICE_IMPL
	public static final String MESSAGE_ERROR_LOGIN_OR_PASSWORD = "Incorect login or password";
	public static final String MESSAGE_ERROR_DATA_USER = "Incorect data user";
	public static final String MESSAGE_ERROR_GET_ALL_DRIVERS = "Error!!! Can`t get all drivers!";
	public static final String MESSAGE_ERROR_GET_USER_BY_ID = "Error!!! Can`t get user by id!";
	
	
	
	//DATABASE_ORDER_DAO
	public static final String SQL_SELECT_ALL_ORDERS = "SELECT orders.id, dep.id as id_city_dep, dep.name as city_name_dep, city.id as id_city_des, city.name as city_name_des, orders.time_departure, orders.count_passenger, users.id as user_id, users.name, users.surname, users.login, users.password, users.phone, roles.title FROM orders INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users ON orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id";
	public static final String SQL_SELECT_ALL_ORDERS_BY_ID = "SELECT orders.id, dep.id as id_city_dep, dep.name as city_name_dep, city.id as id_city_des, city.name as city_name_des, orders.time_departure, orders.count_passenger, users.id as user_id, users.name, users.surname, users.login, users.password, users.phone, roles.title FROM orders INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users ON orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id WHERE users.id = ? ORDER BY orders.time_departure LIMIT ?,?";
	public static final String SQL_TRANSACTION_VARIABLE_ADD_ORDER = "SET @city_id_departure = 0, @city_id_destination = 0, @user_id = 0";
	public static final String SQL_SELECT_TRANSACTION_CITY_DEPARTURE = "SELECT id INTO @city_id_departure FROM city WHERE name = ?";
	public static final String SQL_SELECT_TRANSACTION_CITY_DESTINATION = "SELECT id INTO @city_id_destination FROM city WHERE name = ?;";
	public static final String SQL_SELECT_TRANSACTION_USER = "SELECT id INTO @user_id FROM users WHERE login = ?";
	public static final String SQL_INSERT_ORDER_TRANSACTION = "INSERT INTO  orders (city_id_departure, city_id_destination, time_departure, count_passenger, users_id) VALUES(@city_id_departure, @city_id_destination, ?, ?, @user_id)";
	public static final String SQL_DELETE_UPDATE_ORDER = "DELETE FROM orders WHERE id = ?";
	
	public static final String ID_CITY_DEPARTURE = "id_city_dep";
	public static final String CITY_NAME_DEPARTURE = "city_name_dep";
	public static final String ID_CITY_DESTINATION = "id_city_des";
	public static final String CITY_NAME_DESTINATION = "city_name_des";
	public static final String TIME_DEPARTURE = "time_departure";
	public static final String COUNT_PASSENGER = "count_passenger";
	public static final String USER_ID = "user_id";
	
	public static final String GET_ALL_ORDERS_WITHOT_ROUTE_EXCEPTION = "Error! You cannot get all orders Without Route!";
	public static final String GET_ALL_ORDERS_EXCEPTION_BY_ID = "Error! You cannot get all orders by id!";
	public static final String ADD_ORDER_EXCEPTION = "Error adding order!";
	public static final String REMOVE_ORDER_EXCEPTION = "Error remove order by id!";
	
	//ORDER_SERVICE_IMPL
	public static final String MESSAGE_ERROR_GET_ALL_ORDER = "Error!!! Can`t get all orders!";
	public static final String MESSAGE_ERROR_ADD_ORDER = "Can`t add order!!!";
	public static final String MESSAGE_ERROR_REMOVE_ORDER = "Error! Can`t remove order!";
	public static final String MESSAGE_ERROR_GET_ORDER_BY_ID = "Error! Can`t get order by id!";
	public static final String MESSAGE_ERROR_GET_ALL_ORDER_WITHOUT_ROUTE = "Error!!! Can`t get all orders without routes!";
	public static final String MESSAGE_ERROR_GET_ORDERS_BY_ID = "Error! Can`t get orders by id!";
	public static final String MESSAGE_ERROR_UPDATE_ORDER = "Can`t update order!!!";
	
	
	
	//DATABASE_ROUTE_DAO
	public static final String SQL_SELECT_ALL_ROUTES = "SELECT routes.id, routes.orders_id, dep.id AS id_city_dep, dep.name AS city_name_dep, city.id AS id_city_des, city.name AS city_name_des, orders.time_departure, orders.count_passenger, orders.users_id AS client_id, users.name AS c_name, users.surname as c_surname, users.login AS c_login, users.password AS c_password, users.phone as c_phone, roles.title as c_title, routes.users_id AS driver_id, driv.name AS d_name, driv.surname AS d_surname, driv.login AS d_login, driv.password AS d_password, driv.phone AS d_phone, rol.title AS d_title, routes.done FROM routes INNER JOIN orders ON routes.orders_id = orders.id INNER JOIN users ON  orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users AS driv ON routes.users_id = driv.id INNER JOIN roles as rol ON driv.role_id = rol.id LIMIT ?,?";
	public static final String SQL_SELECT_ALL_ROUTES_BY_ID = "SELECT routes.id, routes.orders_id, dep.id AS id_city_dep, dep.name AS city_name_dep, city.id AS id_city_des, city.name AS city_name_des, orders.time_departure, orders.count_passenger, orders.users_id AS client_id, users.name AS c_name, users.surname as c_surname, users.login AS c_login, users.password AS c_password, users.phone as c_phone, roles.title as c_title, routes.users_id AS driver_id, driv.name AS d_name, driv.surname AS d_surname, driv.login AS d_login, driv.password AS d_password, driv.phone AS d_phone, rol.title AS d_title, routes.done FROM routes INNER JOIN orders ON routes.orders_id = orders.id INNER JOIN users ON  orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users AS driv ON routes.users_id = driv.id INNER JOIN roles as rol ON driv.role_id = rol.id WHERE routes.users_id = ? LIMIT ?,?";
	public static final String SQL_TRANSACTION_VARIABLE_ADD_ROUTE = "SET @orders_id = 0, @users_id = 0";
	public static final String SQL_SELECT_TRANSACTION_ORDERS = "SELECT id INTO @orders_id FROM orders WHERE id = ?";
	public static final String SQL_SELECT_TRANSACTION_USERS = "SELECT id INTO @users_id FROM users WHERE id = ?";
	public static final String SQL_INSERT_ROUTE_TRANSACTION = "INSERT INTO routes(orders_id, users_id, done) VALUES(@orders_id, @users_id, ?)";
	public static final String REMOVE_ROUTE_EXCEPTION = "Error remove route by id!";
	public static final String SQL_DELETE_UPDATE_ROUTE = "DELETE FROM routes WHERE id = ?";
	public static final String SQL_EDIT_MARK_ROUTE = "UPDATE routes SET done=? WHERE routes.id = ?";
	
	public static final String ORDERS_ID = "orders_id";
	public static final String CLIENT_ID = "client_id";
	public static final String CLIENT_NAME = "c_name";
	public static final String CLIENT_SURNAME = "c_surname";
	public static final String CLIENT_LOGIN = "c_login";
	public static final String CLIENT_PASSWORD = "c_password";
	public static final String CLIENT_TITLE = "c_title";
	public static final String CLIENT_PHONE = "c_phone";
	public static final String DONE = "done";
	public static final String DRIVER_ID = "driver_id";
	public static final String DRIVER_NAME = "d_name";
	public static final String DRIVER_SURNAME = "d_surname";
	public static final String DRIVER_LOGIN = "d_login";
	public static final String DRIVER_PASSWORD = "d_password";
	public static final String DRIVER_TITLE = "d_title";
	public static final String DRIVER_PHONE = "d_phone";
	
	public static final String GET_ALL_ROUTES_EXCEPTION = "Error! You cannot get all routes!";
	public static final String ADD_ROUTE_EXCEPTION = "Error adding order!";
	public static final String EDIT_MARK_ROUTE_EXCEPTION = "Error edit mark route!";
	public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	
	//ROUTE_SERVICE_IMPL
	public static final String MESSAGE_ERROR_GET_ALL_ROUTE = "Error!!! Can`t get all routes!";
	public static final String MESSAGE_ERROR_ADD_ROUTE = "Can`t add route!!!";
	public static final String MESSAGE_ERROR_REMOVE_ROUTE = "Error! Can`t remove route!";
	public static final String MESSAGE_ERROR_GET_ROUTE_BY_ID = "Error! Can`t get by id route!";
	public static final String MESSAGE_EMPTY_NAME_EDIT_MARK = "Passed parameter name is empty! edit mark!!";
	public static final String MESSAGE_ERROR_EDIT_MARK_ROUTE = "Error! Can`t edit mark route!";

	//VALIDATION
	public static final String MESSAGE_ERROR_EMPTY_CAR = "Warning!!! The search has not given any results!";
	public static final String MESSAGE_ERROR_CAR_IS_EMPTY = "Passed parameter Car is empty!!";
	public static final String MESSAGE_ERROR_EMPTY_CITY = "Warning!!! The search all cities has not given any results!";
	public static final String MESSAGE_ERROR_CITY_IS_EMPTY = "Passed parameter City is empty!! ";
	public static final String MESSAGE_ERROR_EMPTY_ORDER = "Warning!!! The search all orders has not given any results!";
	public static final String MESSAGE_ERROR_ORDER_IS_EMPTY = "Passed parameter Orders is empty!! ";
	public static final String MESSAGE_ERROR_EMPTY_ROUTE = "Warning!!! The search all routes has not given any results!";
	public static final String MESSAGE_ERROR_ROUTE_IS_EMPTY = "Passed parameter Route is empty!!";
	public static final String MESSAGE_ERROR_LOGIN = "Incorect login!";
	public static final String MESSAGE_ERROR_PASSWORD = "Incorect password!";
	public static final String MESSAGE_ERROR_NAME = "Incorect name!";
	public static final String MESSAGE_ERROR_SURNAME = "Incorect surname!";
	public static final String MESSAGE_ERROR_PHONE = "Incorect phone number!";
	public static final String MESSAGE_ERROR_EMPTY_DRIVER = "Warning!!! The search all drivers has not given any results!";
	public static final String MESSAGE_ERROR_ROLE = "Incorect role";
	public static final String MESSAGE_ERROR_USER = "Passed parameter User is empty!!";
	public static final String MESSAGE_ERROR_FORMAT_PHONE = "Incorect format phone number!";
	public static final String MEESAGE_ERROR_DATE_FORMAT = "Incorect date format! Need like this - 'yyyy-MM-dd HH:mm'";
	public static final String MESSAGE_ERROR_ID = "Incorect id for removing!!!";
	public static final String MESSAGE_ERROR_COUNT_PASSENGER = "Passed parameter count passenger should be from 1 to 4!!";
	public static final String MESSAGE_ERROR_CAR_NUMBER = "Incorrect car number. Must be in XXXX format.";
	public static final String MESSAGE_ERROR_FEEDBACK_IS_EMPTY = "Passed parameter feedback is empty!!";;

	
	//COMMAND IMPL
	
	//AddCar
	public static final String REDIRECT_PATH_TO_DISPATCHER_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=DISPATCHER&AddCarSuccess=Car successful added!";
	public static final String ERROR_ADD_CAR = "errorAddCar";
	public static final String PATH_TO_DISPATCHER_JSP = "/WEB-INF/jsp/Dispatcher.jsp";
	public static final String CAR_MARK = "car_mark";
	public static final String ID_DRIVER = "idDriver";

	//AddFeedback
	public static final String ADD_FEEDBACK_REDIRECT_PATH_TO_CLIENT_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=CLIENT&AddFeedbackSuccess=Feedback successful added!";
	public static final String ERROR_ADD_FEEDBACK = "errorAddFeedback";

	//AppointCar
	public static final String ERROR_APPOINT_CAR_LIST = "errorAppointCarList";
	public static final String DRIVER_APPOINT_CAR = "driver";
	public static final String CAR_LIST = "carList";

	
	//AddOrder
	public static final String CITY_DEPARTURE = "city_departure";
	public static final String CITY_DESTINATION = "city_destination";
	public static final String ERROR_ADD_ORDER = "errorAddOrder";
	public static final String PATH_TO_CLIENT_JSP = "/WEB-INF/jsp/Client.jsp";
	public static final String REDIRECT_PATH_TO_CLIENT_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=CLIENT&AddOrderSuccess=Order successful added!";
	public static final String LOCAL_DATE_TIME_PATTERN_ADD_ORDER = "yyyy-MM-dd HH:mm";
	public static final String MEESAGE_ERROR_TYPE_COUNT_FORMAT = "Input field is empty or not of type int!";
	
	//AddRoute
	public static final String ID_ORDER = "idOrder";
	public static final String PATH_ADD_ROUTE_TO_DISPATCHER_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=DISPATCHER&addRouteSuccess=Route successful added!";
	public static final String ERROR_ADD_ROUTE = "errorAddRoute";
	public static final String PATH_ADD_ROUTE_TO_CREATE_ROUTE_DISPATCHER_JSP = "/WEB-INF/jsp/CreateRouteDispatcher.jsp";

	//ChangeLocale
	public static final String LOCALE = "locale";
	public static final String PAGE = "page";
	public static final String LANGUAGE = "language";
	public static final String PATH_TO_JSP = "/WEB-INF/jsp";
	public static final String DELIMETR = "/";
	public static final String INDEX_JSP = "index.jsp";

	//GetAllCar
	public static final String MESSAGE_CARS_LIST_EMPTY = "messageCarsListEmpty";
	public static final String MESSAGE_IF_CARS_LIST_EMPTY = "You have no cars!";
	public static final String ERROR_GET_ALL_CAR = "errorGetAllCar";
	public static final String CARS_LIST = "carsList";
	public static final String COUNT_ROWS = "countRows";
	public static final String CURRENT_PAGE = "currentPage";
	
	//GetAllCarByIdDriver
	public static final String ERROR_GET_ALL_CAR_BY_ID_DRIVER = "errorGetAllCarByIdDriver";
	
	//GetAllDriver
	public static final String ERROR_DRIVER_LIST = "errorDriverList";
	public static final String LIST_DRIVER = "listDriver";
	
	//GetAllFEEDBACK
	public static final String FEEDBACKS_LIST = "feedbacks";
	public static final String MESSAGE_FEEDBACKS_LIST_EMPTY = "messageFeedbacksListEmpty";
	public static final String MESSAGE_IF_FEEDBACK_LIST_EMPTY = "You have no feedbacks!";
	public static final String ERROR_GET_ALL_FEEDBACK = "errorGetAllFeedbacks";
	public static final String MESSAGE_FOR_ADD_FEEDBACK = "addNewFeedback";


	//GetAllFeedbackById
	public static final String ERROR_GET_ALL_FEEDBACK_BY_ID_DRIVER = "errorGetAllFeedbackByIdClient";
	
	//CreateRoute
	public static final String ERROR_ORDER_BY_ID = "errorOrderById";
	public static final String ORDER_HINT = "orderHint";
	public static final String PATH_CREATE_ROUTE_TO_CREATE_ROUTE_DISPATCHER_JSP = "/WEB-INF/jsp/CreateRouteDispatcher.jsp";

	//EditCarCondition
	public static final String ERROR_EDIT_CAR_CONDITION = "errorEditCarCondition";
	public static final String SELECT_STATUS_CAR = "select_status_car";
	public static final String PATH_REDIRECT_EDIT_CAR_CONDITION_TO_DRIVER_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=DRIVER&editCarConditionSuccess=Car condition was successfully edited!";
	public static final String PATH_FORWARD_EDIT_CAR_CONDITION_TO_DRIVER_JSP = "/WEB-INF/jsp/Driver.jsp";

	//EditMarkRoute
	public static final String ERROR_EDIT_MARK_ROUTE = "errorEditMarkRoute";
	public static final String SELECT_MARK = "select_mark";
	public static final String PATH_FORWARD_EDIT_MARK_ROUTE_TO_DRIVER_JSP = "/WEB-INF/jsp/Driver.jsp";
	public static final String PATH_REDIRECT_EDIT_MARK_ROUTE_TO_DRIVER_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=DRIVER&editMarkRouteSuccess=Mark route was successfully edited!";

	//GetAllOrderWithoutRoute
	public static final String MESSAGE_ORDERS_LIST_EMPTY = "messageOrdersListEmpty";
	public static final String MESSAGE_IF_ORDERS_LIST_EMPTY = "You have no orders!";
	public static final String ERROR_GET_ALL_ORDER_WITHOUT_ROUTE = "errorGetAllOrderWithoutRoute";

	//GetAllOrderByID
	public static final String ORDER_LIST = "orderList";
	public static final String USER_ID_PAR = "userId";
	public static final String MESSAGE_ORDER_LIST_EMPTY = "messageOrderListEmpty";
	public static final String MESSAGE_IF_ORDER_LIST_EMPTY = "You have no orders!";
	public static final String ERROR_ORDER_LIST = "errorOrderListEmpty";
	
	//GoToAddOrder
	public static final String MESSAGE_FOR_ADD_ROUTE = "addNewOrder";
	
	//GetAllRouteByID
	public static final String PATH_TO_DRIVER_JSP = "/WEB-INF/jsp/Driver.jsp";
	public static final String ROUTE_LIST = "routeList";
	public static final String MESSAGE_ROUTE_LIST_EMPTY = "messageRouteListEmpty";
	public static final String MESSAGE_IF_ROUTE_LIST_EMPTY = "You have no routes!";
	public static final String ERROR_GET_ALL_ROUTE = "errorGetAllRoute";

	//GoToRegistration
	public static final String ROLE_REGISTR = "roleRegistr";

	//logout
	public static final String PATH_TO_INDEX_JSP = "index.jsp";

	//Registration
	public static final String ROLE = "role";
	public static final String ERROR_REGISTRATION = "errorRegistration";
	public static final String USER = "user";
	public static final String PATH_TO_REGISTRATION_JSP = "/WEB-INF/jsp/Registration.jsp";
	public static final String HIDDEN_ROLE = "hiddenRole";
	public static final String CLIENT_LOWER_CASE = "client";

	//RemoveFeedback
	public static final String REMOVE_FEEDBACK_REDIRECT_PATH_TO_DISPATCHER_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=CLIENT&removeFeedbackSuccess=Feedback successful removed!";
	public static final String ERROR_FEEDBACK_REMOVE = "errorFeedbackRemove";
	public static final String MESSAGE_ERROR_FEEDBACK_REMOVE = "Cannot delete feedback!";

	
	//RemoveCar
	public static final String ERROR_CAR_REMOVE = "errorCarRemove";
	public static final String MESSAGE_ERROR_CAR_REMOVE = "Cannot delete car!";
	public static final String REMOVE_CAR_REDIRECT_PATH_TO_DISPATCHER_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=DISPATCHER&removeCarSuccess=Car successful removed!";
	
	//RemoveOrder
	public static final String ERROR_ORDER_REMOVE = "ErrorOrderRemove";
	public static final String MESSAGE_ERROR_ORDER_REMOVE = "Cannot delete order!";
	public static final String REMOVE_ORDER_REDIRECT_PATH_TO_CLIENT_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=CLIENT&removeOrderSuccess=Order successful removed!";

	//RemoveRoute
	public static final String ERROR_ROUTE_REMOVE = "ErrorRouteRemove";
	public static final String MESSAGE_ERROR_ROUTE_REMOVE = "Cannot delete route!";
	public static final String REMOVE_ROUTE_REDIRECT_PATH_TO_DISPATCHER_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=DISPATCHER&removeRouteSuccess=Route successful removed!";

	//SelectPage
	public static final String ADD_ORDER_SUCCESS = "AddOrderSuccess";
	public static final String REMOVE_ORDER_SUCCESS = "removeOrderSuccess";
	public static final String CLIENT = "CLIENT";
	public static final String DRIVER = "DRIVER";
	public static final String DISPATCHER = "DISPATCHER";
	
	//ShowEditFeedbackForm
	public static final String SINGLE_FEEDBACK = "singleFeedback";
	public static final String ERROR_GET_FEEDBACK_BY_ID = "errorGetfeedbackById";
	public static final String MESSAGE_ERROR_GET_FEEDBACK_BY_ID = "Error! Can`t get feedback by id!";
	public static final String ERROR_SHOW_EDIT_FEEDBACK_FORM = "Could not find feedback!";

	
	//ShowEditOrderForm
	public static final String SINGLE_ORDER = "singleOrder";
	public static final String ERROR_GET_ORDER_BY_ID = "errorGetOrderById";
	public static final String ERROR_SHOW_EDIT_ORDER_FORM = "Could not find order!";
	
	//SingIn
	public static final String USER_NAME = "userName";
	public static final String USER_SURNAME = "userSurname";
	public static final String PATH_TO_DEFINE_PAGE_BY_ROLE_JSP = "WEB-INF/jsp/DefinePageByRole.jsp";
	public static final String PATH_TO_SING_IN_JSP = "/WEB-INF/jsp/SingIn.jsp";
	public static final String ERROR_SING_IN = "errorSingIn";
	public static final String MESSAGE_IF_ERROR_SING_IN = "User not registered!";
	public static final String ERROR_DATA = "errorData";
	public static final String MESSAGE_IF_ERROR_DATA = "wrong password or login!";

	//UpdateOrder
	public static final String ERROR_UPDATE_ORDER = "errorUpdateOrder";
	public static final String REDIRECT_UPDATE_PATH_TO_CLIENT_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=CLIENT&UpdateOrderSuccess=Order successful updated!";
	public static final String ID_USER = "idUser";

	//UpdateFeedback
	public static final String REDIRECT_UPDATE_FEEDBACK_PATH_TO_CLIENT_JSP = "http://localhost:8080/CarBase/Controller?command=select_page&page=CLIENT&UpdateFeedbackSuccess=Feedback successful updated!";
	public static final String ERROR_UPDATE_FEEDBACK = "errorUpdateFeedback";
	public static final String FEEDBACK = "feedback";
	public static final String ID_CLIENT = "idClient";;

}
