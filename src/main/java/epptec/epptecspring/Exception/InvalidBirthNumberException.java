package epptec.epptecspring.Exception;

public class InvalidBirthNumberException extends Throwable {
	public InvalidBirthNumberException(){
		super("Invalid birthNumber added. Add birth number in YYMMDDXXXX or YYMMDD/XXXX format.");
	}
}
