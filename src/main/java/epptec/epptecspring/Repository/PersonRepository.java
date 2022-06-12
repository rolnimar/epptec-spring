package epptec.epptecspring.Repository;


import epptec.epptecspring.Entity.Person;
import epptec.epptecspring.Exception.PersonAlreadyExistsException;
import epptec.epptecspring.Exception.PersonNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonRepository {
	Person fetchPersonByBirthNumber(String birthId) throws PersonNotFoundException;
	void savePerson(Person person) throws PersonAlreadyExistsException;
	void deletePersonByBirthNumber(String birthId) throws PersonNotFoundException;
	Collection<Person> fetchAllPersons();
}
