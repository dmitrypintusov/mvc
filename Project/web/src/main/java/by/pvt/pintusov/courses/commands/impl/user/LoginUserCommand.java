package by.pvt.pintusov.courses.commands.impl.user;

import by.pvt.pintusov.courses.commands.AbstractCommand;
import by.pvt.pintusov.courses.constants.MessageConstants;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.constants.UserType;
import by.pvt.pintusov.courses.entities.User;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.managers.ConfigurationManager;
import by.pvt.pintusov.courses.managers.MessageManager;
import by.pvt.pintusov.courses.services.impl.UserServiceImpl;
import by.pvt.pintusov.courses.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Login user command
 * @author pintusov
 * @version 1.0
 */

public class LoginUserCommand extends AbstractCommand {
	private User user;

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		HttpSession session = request.getSession();
		try {
			user = RequestParameterParser.getUser(request);
			if (UserServiceImpl.getInstance ().checkUserAuthorization(user.getLogin(), user.getPassword())) {
				user = UserServiceImpl.getInstance().getUserByLogin(user.getLogin());
				UserType userType = UserServiceImpl.getInstance().checkAccessType(user);
				session.setAttribute(Parameters.USER, user);
				session.setAttribute(Parameters.USER_TYPE, userType);
				if (UserType.STUDENT.equals(userType)) {
					page = ConfigurationManager.getInstance().getProperty(PagePath.STUDENT_PAGE_PATH);
				} else {
					page = ConfigurationManager.getInstance().getProperty(PagePath.TEACHER_PAGE_PATH);
				}
			} else  {
				page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
				request.setAttribute(Parameters.ERROR_LOGIN_OR_PASSWORD, MessageManager.getInstance().getProperty(MessageConstants.WRONG_LOGIN_OR_PASSWORD));
			}
		} catch (ServiceException | SQLException e) {
			page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
			request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
		}
		return page;
	}
}
