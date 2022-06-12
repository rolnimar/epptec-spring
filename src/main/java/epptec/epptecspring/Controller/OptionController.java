package epptec.epptecspring.Controller;


import epptec.epptecspring.Entity.Person;
import epptec.epptecspring.Exception.EmptyInputException;
import epptec.epptecspring.Exception.InvalidBirthNumberException;
import epptec.epptecspring.Exception.PersonAlreadyExistsException;
import epptec.epptecspring.Exception.PersonNotFoundException;
import epptec.epptecspring.Service.IOService;
import epptec.epptecspring.Service.PersonService;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class OptionController {
	private final IOService ioService;
	private final PersonService personService;
	private boolean exit = false;

	public OptionController(IOService ioService, PersonService personService){
		this.ioService=ioService;
		this.personService = personService;
	}

	public void init() {

		do {
			ioService.printOptions();
			String option = ioService.scanString();
			switch (option) {
				case "1" -> handleFindPerson();
				case "2" -> handleCreatePerson();
				case "3" -> handleDeletePerson();
				case "4" -> exit = true;
				default -> ioService.printInvalid();
			}
		} while (!exit);
	}

	private void handleDeletePerson() {
		Collection<Person> personList = personService.getAllPersons();
		if(personList.isEmpty()){
			ioService.printLine("Database is empty.");
			handleNextAction();
			return;
		}
		ioService.printAllPersons(personList);
		ioService.printChoosePerson();
		String birthNumber = ioService.scanBirthNumber();
		try{
			personService.deletePersonByBirthNumber(birthNumber);
			ioService.printCurrentDatabaseState(personService.getAllPersons());
		} catch (PersonNotFoundException ex){
			ioService.printLine(ex.getMessage());
		}
		handleNextAction();
	}

	private void handleCreatePerson() {
		ioService.printCreatePerson();
		ioService.printBirthNumberPrompt();
		String birthNumber = ioService.scanBirthNumber();
		ioService.printNamePrompt();
		String name = ioService.scanString();
		ioService.printSurnamePrompt();
		String surname = ioService.scanString();
		try{
			Person person = Person.builder().name(name).surname(surname).birthNumber(birthNumber).build();
			personService.savePerson(person);
			ioService.printCurrentDatabaseState(personService.getAllPersons());
		} catch (PersonAlreadyExistsException | InvalidBirthNumberException | EmptyInputException ex){
			ioService.printLine(ex.getMessage());
		}
		handleNextAction();
	}

	private void handleFindPerson() {
		ioService.printBirthNumberInput();
		String birthNumber = ioService.scanBirthNumber();
		try{
			Person person = personService.findPerson(birthNumber);
			ioService.printPerson(person);
		} catch(PersonNotFoundException ex){
			ioService.printLine(ex.getMessage());
		}
		handleNextAction();
	}

	private void handleNextAction() {
		ioService.printContinue();
		String input = ioService.scanString();
		while(!input.equals("n") && !input.equals("y")){
			ioService.wrongContinueInput();
			input = ioService.scanString();
		}
		if(input.equals("n")){
			exit = true;
		}

	}
}
