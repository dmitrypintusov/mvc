package by.pvt.pintusov.courses.entities;

/**
 * Describes the entity <strong>Course</strong>
 * @author pintusov
 * @version 1.0
 */
public class Course extends Entity {
	private static final long serialVersionUID = 1L;
	private String courseName;
	private int hours;
	private int status;

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName setting courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours setting courseName
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status setting status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof Course)) return false;
		if (!super.equals(o)) return false;
		Course other = (Course) o;
		if (hours != other.hours) return false;
		if (status != other.status) return false;
		return courseName.equals(other.courseName);
	}

	@Override
	public int hashCode() {
		final int alfa = 31;
		int result = super.hashCode();
		result = alfa * result + courseName.hashCode();
		result = alfa * result + hours;
		result = alfa * result + status;
		return result;
	}

	@Override
	public String toString() {
		return "Course {courseName=" + courseName + ", hours=" + hours + ", status=" + status + "}";
	}
}
