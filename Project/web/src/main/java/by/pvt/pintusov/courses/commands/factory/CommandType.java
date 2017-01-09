package by.pvt.pintusov.courses.commands.factory;

import by.pvt.pintusov.courses.commands.ICommand;
import by.pvt.pintusov.courses.commands.impl.teacher.GoBackTeacherCommand;
import by.pvt.pintusov.courses.commands.impl.teacher.ShowOperationsCommand;
import by.pvt.pintusov.courses.commands.impl.teacher.ShowStudentsCommand;
import by.pvt.pintusov.courses.commands.impl.user.*;

/**
 * Command factory
 * @author pintusov
 * @version 1.0
 */

public enum CommandType {
	//users commands
	LOGIN, LOGOUT, REGISTRATION, GO_TO_REGISTRATION, BACK,

	//students commands
	COURSES_LIST, ATTEND_COURSE, LEAVE_COURSE, BACK_STUDENT,

	//teachers commands
	STUDENTS_LIST, OPERATIONS, START_COURSE, CLOSE_COURSE,
	GO_TO_START_COURSES, GO_TO_CLOSE_COURSES, COURSES_IN_ARCHIVE, BACK_TEACHER;

	//TODO доделать
	/**
	 * Gets current command
	 * @return command depending on request
	 */
	public ICommand getCurrentCommand () throws EnumConstantNotPresentException {
		switch (this) {
			//user commands
			case LOGIN:
				return new LoginUserCommand();
			case LOGOUT:
				return new LogoutUserCommand();
			case REGISTRATION:
				return new RegistrationCommand();
			case GO_TO_REGISTRATION:
				return new GoToRegistrationCommand();
			case BACK:
				return new GoBackCommand();
			//teacher`s commands
			case STUDENTS_LIST:
				return new ShowStudentsCommand();
			case OPERATIONS:
				return new ShowOperationsCommand();
			case BACK_TEACHER:
				return new GoBackTeacherCommand();
			//students commands


			default:
				return new LoginUserCommand();
		}
	}
}
