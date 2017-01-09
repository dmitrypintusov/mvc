package by.pvt.pintusov.courses.entities;

/**
 * Describes the entity <strong>User</strong>
 * @author pintusov
 * @version 1.0
 */

public class User extends Entity {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private int courseId;
	private int accessType;

	/**
	 * @return the firstName
	 */
	public String getFirstName () { return firstName; }

	/**
	 * @param firstName setting firstName
	 */
	public void setFirstName (String firstName) { this.firstName = firstName; }

	/**
	 * @return the lastName
	 */
	public String getLastName () { return lastName; }

	/**
	 * @param lastName setting lastName
	 */
	public void setLastName (String lastName) { this.lastName = lastName; }

	/**
	 * @return the login
	 */
	public String getLogin () { return login; }

	/**
	 * @param login setting login
	 */
	public void setLogin (String login) { this.login = login; }

	/**
	 * @return the password
	 */
	public String getPassword () { return password; }

	/**
	 * @param password setting password
	 */
	public void setPassword (String password) { this.password = password; }

	/**
	 * @return the courseId;
	 */
	public int getCourseId () { return courseId; }

	/**
	 * @param courseId setting courseId
	 */
	public void setCourseId (int courseId) { this.courseId = courseId; }

	/**
	 * @return the accessType
	 */
	public int getAccessType() { return accessType; }

	/**
	 * @param accessType setting accessType
	 */
	public void setAccessType(int accessType) { this.accessType = accessType; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		User user = (User) o;

		if (courseId != user.courseId) return false;
		if (accessType != user.accessType) return false;
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
		if (login != null ? !login.equals(user.login) : user.login != null) return false;
		return password != null ? password.equals(user.password) : user.password == null;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + courseId;
		result = 31 * result + accessType;
		return result;
	}

	@Override
	public String toString() {
		return "User {firstName=" + firstName + ", lastName=" + lastName + ", login=" + login +
				", password=" + password + ", courseId=" + courseId + ", accessType=" + accessType + "}";
	}
}
