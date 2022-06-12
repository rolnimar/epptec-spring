package epptec.epptecspring.Exception;

public class PersonNotFoundException extends Throwable{
	public PersonNotFoundException(){
		super("Person was not found in the database.");
	}
}
