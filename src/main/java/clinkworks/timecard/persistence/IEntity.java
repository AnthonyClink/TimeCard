package clinkworks.timecard.persistence;

import java.io.Serializable;

public interface IEntity<ID extends Serializable>{
	public ID getId();
}
