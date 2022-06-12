package epptec.epptecspring.Controller;

import epptec.epptecspring.Entity.Person;
import epptec.epptecspring.Exception.PersonNotFoundException;
import epptec.epptecspring.Service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PersonRestController {
	private final PersonService personService;

	public PersonRestController(PersonService personService){
		this.personService = personService;
	}

	@GetMapping(path = "/persons/{birthNumber}",
							produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getPersonByBirthNumber(@PathVariable String birthNumber) {
		try {
			return personService.findPerson(birthNumber);
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}


}
