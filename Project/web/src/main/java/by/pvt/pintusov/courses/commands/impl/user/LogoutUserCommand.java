package by.pvt.pintusov.courses.commands.impl.user;

import by.pvt.pintusov.courses.commands.AbstractCommand;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.managers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Logout user command
 * @author pintusov
 * @version 1.0
 */

public class LogoutUserCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
		request.getSession().invalidate();
		return page;
	}
}
