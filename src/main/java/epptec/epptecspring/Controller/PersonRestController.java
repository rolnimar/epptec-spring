package epptec.epptecspring.Controller;

import epptec.epptecspring.Entity.Person;
import epptec.epptecspring.Exception.EmptyInputException;
import epptec.epptecspring.Exception.InvalidBirthNumberException;
import epptec.epptecspring.Exception.PersonAlreadyExistsException;
import epptec.epptecspring.Exception.PersonNotFoundException;
import epptec.epptecspring.Service.IOService;
import epptec.epptecspring.Service.PersonService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PersonRestController {
	private final PersonService personService;
	private final IOService ioService;

	public PersonRestController(PersonService personService, IOService ioService){
		this.personService = personService;
		this.ioService = ioService;
	}

	@GetMapping(path = "/person/{birthNumber}",
							produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getPersonByBirthNumber(@PathVariable String birthNumber) {
		try {
			HttpHeaders headers = new HttpHeaders();
			Person person = personService.findPerson(birthNumber);
			return new ResponseEntity<>(person,headers,HttpStatus.FOUND);
		} catch (PersonNotFoundException e) {
			ioService.printLine(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/person",
							consumes = MediaType.APPLICATION_JSON_VALUE,
							produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> createPerson(@RequestBody Person newPerson) {
		try{
			personService.savePerson(newPerson);
			return new ResponseEntity<>(newPerson,HttpStatus.CREATED);
		} catch (PersonAlreadyExistsException ex){
				ioService.printLine(ex.getMessage());
				throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
		} catch (InvalidBirthNumberException | EmptyInputException ex){
			ioService.printLine(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage(), ex);
		}
	}

	@DeleteMapping(path = "/person/{birtNumber}",
								produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePerson(@PathVariable String birtNumber){
		try {
			personService.deletePersonByBirthNumber(birtNumber);
			return new ResponseEntity<>(birtNumber,HttpStatus.OK);
		} catch (PersonNotFoundException ex) {
			ioService.printLine(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage(),ex);
		}
	}
}
