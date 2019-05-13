package by.epam.javawebtraining.glazunov.webproject.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.command.impl.AddCar;
import by.epam.javawebtraining.glazunov.webproject.command.impl.AddOrder;
import by.epam.javawebtraining.glazunov.webproject.command.impl.AddRoute;
import by.epam.javawebtraining.glazunov.webproject.command.impl.AppointCar;
import by.epam.javawebtraining.glazunov.webproject.command.impl.ChangeLocale;
import by.epam.javawebtraining.glazunov.webproject.command.impl.CreateRoute;
import by.epam.javawebtraining.glazunov.webproject.command.impl.EditCarCondition;
import by.epam.javawebtraining.glazunov.webproject.command.impl.EditMarkRoute;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GetAllCar;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GetAllCarByIdDriver;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GetAllDriver;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GetAllOrderById;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GetAllOrderWithoutRoute;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GetAllRoute;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GetAllRouteById;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GoToAddOrder;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GoToRegistration;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GoToSingIn;
import by.epam.javawebtraining.glazunov.webproject.command.impl.Logout;
import by.epam.javawebtraining.glazunov.webproject.command.impl.Registration;
import by.epam.javawebtraining.glazunov.webproject.command.impl.RemoveCar;
import by.epam.javawebtraining.glazunov.webproject.command.impl.RemoveOrder;
import by.epam.javawebtraining.glazunov.webproject.command.impl.RemoveRoute;
import by.epam.javawebtraining.glazunov.webproject.command.impl.SingIn;
import by.epam.javawebtraining.glazunov.webproject.command.impl.UpdateOrder;
import by.epam.javawebtraining.glazunov.webproject.command.impl.SelectPage;
import by.epam.javawebtraining.glazunov.webproject.command.impl.ShowEditOrderForm;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.SELECT_PAGE, new SelectPage());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
		commands.put(CommandName.GO_TO_SING_IN, new GoToSingIn());
		commands.put(CommandName.GO_TO_REGISTRATION, new GoToRegistration());
		commands.put(CommandName.GO_TO_ADD_ORDER, new GoToAddOrder());
		commands.put(CommandName.ADD_CAR, new AddCar());
		commands.put(CommandName.SING_IN, new SingIn());
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.GET_ALL_ORDER_BY_ID, new GetAllOrderById());
		commands.put(CommandName.ADD_ORDER, new AddOrder());
		commands.put(CommandName.REMOVE_ORDER, new RemoveOrder());
		commands.put(CommandName.LOGOUT, new Logout());
		commands.put(CommandName.GET_ALL_ROUTE_BY_ID, new GetAllRouteById());
		commands.put(CommandName.EDIT_MARK_ROUTE, new EditMarkRoute());
		commands.put(CommandName.EDIT_CAR_CONDITION, new EditCarCondition());
		commands.put(CommandName.CREATE_ROUTE, new CreateRoute());
		commands.put(CommandName.ADD_ROUTE, new AddRoute());
		commands.put(CommandName.REMOVE_ROUTE, new RemoveRoute());
		commands.put(CommandName.GET_ALL_CAR_BY_ID_DRIVER, new GetAllCarByIdDriver());
		commands.put(CommandName.GET_ALL_ORDER_WITHOUT_ROUTE, new GetAllOrderWithoutRoute());
		commands.put(CommandName.GET_ALL_ROUTE, new GetAllRoute());
		commands.put(CommandName.GET_ALL_CAR, new GetAllCar());
		commands.put(CommandName.SHOW_EDIT_ORDER_FORM, new ShowEditOrderForm());
		commands.put(CommandName.UPDATE_ORDER, new UpdateOrder());
		commands.put(CommandName.REMOVE_CAR, new RemoveCar());
		commands.put(CommandName.GET_ALL_DRIVER, new GetAllDriver());
		commands.put(CommandName.APPOINT_CAR, new AppointCar());
	}

	public Command getCommand(String commandName) {
		commandName = commandName.toUpperCase();
		return commands.get(CommandName.valueOf(commandName));
	}
}
