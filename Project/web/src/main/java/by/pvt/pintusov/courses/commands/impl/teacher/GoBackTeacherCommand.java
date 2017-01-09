package by.pvt.pintusov.courses.commands.impl.teacher;

import by.pvt.pintusov.courses.commands.AbstractCommand;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.UserType;
import by.pvt.pintusov.courses.managers.ConfigurationManager;
import by.pvt.pintusov.courses.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Go back teacher command
 * @author pintusov
 * @version 1.0
 */

public class GoBackTeacherCommand extends AbstractCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		HttpSession session = request.getSession();
		UserType userType = RequestParameterParser.getUserType(request);
		if (userType == UserType.TEACHER) {
			page = ConfigurationManager.getInstance().getProperty(PagePath.TEACHER_PAGE_PATH);
		} else {
			page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
			session.invalidate();
		}
		return page;
	}
}
