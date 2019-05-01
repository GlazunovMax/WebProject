package by.epam.javawebtraining.glazunov.webproject.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.javawebtraining.glazunov.webproject.command.Command;
import by.epam.javawebtraining.glazunov.webproject.command.impl.AddCar;
import by.epam.javawebtraining.glazunov.webproject.command.impl.AddOrder;
import by.epam.javawebtraining.glazunov.webproject.command.impl.AddRoute;
import by.epam.javawebtraining.glazunov.webproject.command.impl.ChangeLocale;
import by.epam.javawebtraining.glazunov.webproject.command.impl.CreateRoute;
import by.epam.javawebtraining.glazunov.webproject.command.impl.EditCarCondition;
import by.epam.javawebtraining.glazunov.webproject.command.impl.EditMarkRoute;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GetAllCarByIdDriver;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GetAllOrderById;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GetAllRouteById;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GoToRegistration;
import by.epam.javawebtraining.glazunov.webproject.command.impl.GoToSingIn;
import by.epam.javawebtraining.glazunov.webproject.command.impl.Logout;
import by.epam.javawebtraining.glazunov.webproject.command.impl.Registration;
import by.epam.javawebtraining.glazunov.webproject.command.impl.RemoveOrder;
import by.epam.javawebtraining.glazunov.webproject.command.impl.RemoveRoute;
import by.epam.javawebtraining.glazunov.webproject.command.impl.SingIn;

import by.epam.javawebtraining.glazunov.webproject.command.impl.SelectPage;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.SELECT_PAGE, new SelectPage());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
		commands.put(CommandName.GO_TO_SING_IN, new GoToSingIn());
		commands.put(CommandName.GO_TO_REGISTRATION, new GoToRegistration());
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
		
	}

	public Command getCommand(String commandName) {
		commandName = commandName.toUpperCase();
		return commands.get(CommandName.valueOf(commandName));
	}
}
