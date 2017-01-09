package by.pvt.pintusov.courses.commands.impl.teacher;

import by.pvt.pintusov.courses.commands.AbstractCommand;
import by.pvt.pintusov.courses.constants.MessageConstants;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.constants.UserType;
import by.pvt.pintusov.courses.entities.Operation;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.managers.ConfigurationManager;
import by.pvt.pintusov.courses.managers.MessageManager;
import by.pvt.pintusov.courses.services.impl.OperationServiceImpl;
import by.pvt.pintusov.courses.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Show operation command
 * @author pintusov
 * @version 1.0
 */

public class ShowOperationsCommand extends AbstractCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		HttpSession session = request.getSession();
		UserType userType = RequestParameterParser.getUserType(request);
		if (userType == UserType.TEACHER) {
			try {
				List<Operation> operations = OperationServiceImpl.getInstance().getAll();
				session.setAttribute(Parameters.OPERATIONS_LIST, operations);
				page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
				request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
			} catch (ServiceException | SQLException e) {
				page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
				request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
			}
		} else {
			page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
			session.invalidate();
		}
		return page;
	}
}
