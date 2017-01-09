package by.pvt.pintusov.courses.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * Command interface
 * @author pintusov
 * @version 1.0
 */

public interface ICommand {
	String execute (HttpServletRequest request);
}