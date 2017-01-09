package by.pvt.pintusov.courses.entities;
import java.io.Serializable;

/**
 * Describes the abstract entity <strong>Entity</strong>
 * @author pintusov
 * @version 1.0
 */
public abstract class Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int id;

	/**
	 * Creates new entity
	 */
	public Entity () {}

	/**
	 * Creates new entity using id
	 */
	public Entity (int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id setting id
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode () {
		final int alfa = 17;
		int result = 1;
		result = alfa * result + id;
		return result;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) {return true;}
		if (o == null || !(o instanceof Entity)) {return false;}
		Entity other = (Entity) o;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Entity {id=" + id + "}";
	}
}
