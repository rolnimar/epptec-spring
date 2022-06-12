package epptec.epptecspring.Service;


import epptec.epptecspring.Entity.Person;
import epptec.epptecspring.Exception.PersonAlreadyExistsException;
import epptec.epptecspring.Exception.PersonNotFoundException;
import epptec.epptecspring.Repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

	private final PersonRepository personRepository;
	private final BirthNumberService birthNumberService;


	public PersonService(BirthNumberService birthNumberService,
											 PersonRepository personRepository){
		this.birthNumberService = birthNumberService;
		this.personRepository = personRepository;
	}

	public void savePerson(Person person) throws PersonAlreadyExistsException {
		personRepository.savePerson(person);
	}


	public Person findPerson(String birthNumber) throws PersonNotFoundException {
		Person person = personRepository.fetchPersonByBirthNumber(birthNumber);
		person.setAge(birthNumberService.calculateAgeFromBirthNumber(birthNumber));
		return person;
	}

	public Collection<Person> getAllPersons() {
		Collection<Person> personCollection = personRepository.fetchAllPersons();
		for(Person person: personCollection){
			person.setAge(calculateAge(person));
		}
		return personCollection;
	}

	public void deletePersonByBirthNumber(String birthNumber) throws PersonNotFoundException {
		personRepository.deletePersonByBirthNumber(birthNumber);
	}

	private Integer calculateAge(Person person){
		return birthNumberService.calculateAgeFromBirthNumber(person.getBirthNumber());
	}
}
