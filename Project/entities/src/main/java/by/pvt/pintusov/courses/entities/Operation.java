package by.pvt.pintusov.courses.entities;

/**
 * Describes the entity <strong>Operation</strong>
 * @author pintusov
 * @version 1.0
 */

public class Operation extends Entity {
	private static final long serialVersionUID = 1L;
	private int userId;
	private int courseId;
	private String description;
	private String date;

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId setting userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the courseId
	 */

	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId setting courseId
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description setting description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date setting date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Operation operation = (Operation) o;
		if (userId != operation.userId) return false;
		if (courseId != operation.courseId) return false;
		if (!description.equals(operation.description)) return false;
		return date.equals(operation.date);
	}

	@Override
	public int hashCode() {
		final int alfa = 17;
		int result = super.hashCode();
		result = alfa * result + userId;
		result = alfa * result + courseId;
		result = alfa * result + description.hashCode();
		result = alfa * result + date.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Operation{userId=" + userId + ", courseId=" + courseId + ", description=" + description + ", date=" + date + "}";
	}
}
