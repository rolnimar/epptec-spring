package epptec.epptecspring.Exception;

public class PersonAlreadyExistsException extends Throwable {
	public PersonAlreadyExistsException() {
		super("Person with this birth number already exists in the database.");
	}
}
