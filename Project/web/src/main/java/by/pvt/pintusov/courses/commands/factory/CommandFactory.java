package by.pvt.pintusov.courses.commands.factory;

import by.pvt.pintusov.courses.commands.ICommand;
import by.pvt.pintusov.courses.commands.impl.user.LoginUserCommand;
import by.pvt.pintusov.courses.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;

/**
 * Command factory
 * @author pintusov
 * @version 1.0
 */

public class CommandFactory {
	private static CommandFactory instance;

	private CommandFactory () {}

	/**
	 * Singleton for command factory
	 * @return instance of Command factory
	 */
	public static synchronized CommandFactory getInstance () {
		if (instance == null) {
			instance = new CommandFactory();
		}
		return instance;
	}

	/**
	 * Choose command to call depending on request
	 * @param request - request
	 * @return current command
	 */
	public ICommand defineCommand (HttpServletRequest request) {
		ICommand current = null;
		try{
			CommandType type = RequestParameterParser.getCommandType(request);
			current = type.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			current = new LoginUserCommand();
		}
		return current;
	}
}
