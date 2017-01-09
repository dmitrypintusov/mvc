package by.pvt.pintusov.courses.commands.impl.user;

import by.pvt.pintusov.courses.commands.AbstractCommand;
import by.pvt.pintusov.courses.constants.MessageConstants;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.entities.Course;
import by.pvt.pintusov.courses.entities.User;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.managers.ConfigurationManager;
import by.pvt.pintusov.courses.managers.MessageManager;
import by.pvt.pintusov.courses.services.impl.UserServiceImpl;
import by.pvt.pintusov.courses.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Registration command
 * @author pintusov
 * @version 1.0
 */

public class RegistrationCommand extends AbstractCommand {
	private User user;
	private Course course;
	private String courseIdString;

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			user = RequestParameterParser.getUser(request);
			courseIdString = request.getParameter(Parameters.COURSE_ID);
			if (areFieldsFullStocked()) {
				course = RequestParameterParser.getCourse(request);
				if (UserServiceImpl.getInstance().checkIsNewUser(user)) {
					UserServiceImpl.getInstance().registerUser(user, course);
					page = ConfigurationManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
					request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.SUCCESS_OPERATION));
				} else {
					page = ConfigurationManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
					request.setAttribute(Parameters.ERROR_USER_EXISTS, MessageManager.getInstance().getProperty(MessageConstants.USER_EXISTS));
				}
			} else {
				request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.EMPTY_FIELDS));
				page = ConfigurationManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
			}
		} catch (ServiceException | SQLException e) {
			page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
			request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
		} catch (NumberFormatException e) {
			request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
			page = ConfigurationManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
		}
		//TODO modify
		catch (NullPointerException e) {
			page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
		}
		return page;
	}

	//TODO javascript
	private boolean areFieldsFullStocked () {
		boolean isFullStocked = false;
		if (!user.getFirstName().isEmpty()
			& !user.getLastName().isEmpty()
			& !user.getLogin().isEmpty()
			& !user.getPassword().isEmpty()) {
			isFullStocked = true;
		}
		return isFullStocked;
	}
}
