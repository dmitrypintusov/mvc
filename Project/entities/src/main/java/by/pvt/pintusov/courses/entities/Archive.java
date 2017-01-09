package by.pvt.pintusov.courses.entities;

import java.util.HashMap;

/**
 * Describes the entity <strong>Archive</strong>
 * @author pintusov
 * @version 1.0
 */
public class Archive extends Entity {
	private static final long serialVersionUID = 1L;
	private HashMap <User, Integer> marks;
	private int courseId;

	/**
	 * @return the students marks
	 */
	public HashMap<User, Integer> getMarks() {
		return marks;
	}

	/**
	 * @param marks setting marks to students
	 */
	public void setMarks(HashMap<User, Integer> marks) {
		this.marks = marks;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof Archive)) return false;
		if (!super.equals(o)) return false;
		Archive archive = (Archive) o;
		return courseId == archive.courseId;
	}

	@Override
	public int hashCode() {
		final int alfa = 17;
		int result = super.hashCode();
		result = alfa * result + courseId;
		return result;
	}

	@Override
	public String toString() {
		return "Archive{marks=" + marks + ", courseId=" + courseId + "}";
	}
}
