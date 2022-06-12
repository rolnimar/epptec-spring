package epptec.epptecspring.Repository;

import epptec.epptecspring.Entity.Person;
import epptec.epptecspring.Exception.PersonAlreadyExistsException;
import epptec.epptecspring.Exception.PersonNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

	private final HashMap<String, Person> personDatabase = new HashMap<>();

	@Override
	public Person fetchPersonByBirthNumber(String birthId) throws PersonNotFoundException {
		Person person = personDatabase.get(birthId);
		if(person == null){
			throw new PersonNotFoundException();
		} else {
			return person;
		}
	}

	@Override
	public void savePerson(Person person) throws PersonAlreadyExistsException {
		if(personDatabase.get(person.getBirthNumber()) != null){
			throw new PersonAlreadyExistsException();
		} else {
			personDatabase.put(person.getBirthNumber(),person);
		}
	}

	@Override
	public void deletePersonByBirthNumber(String birthId) throws PersonNotFoundException {
		if(personDatabase.remove(birthId) == null){
			throw new PersonNotFoundException();
		}
	}

	@Override
	public Collection<Person> fetchAllPersons() {
		return personDatabase.values();
	}
}
