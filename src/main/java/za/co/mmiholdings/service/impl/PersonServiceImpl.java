package za.co.mmiholdings.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import za.co.mmiholdings.schema.Person;
import za.co.mmiholdings.service.PersonException;
import za.co.mmiholdings.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	private Map<Integer, Person> dataSource;

	public void addPerson(Person person) throws PersonException {
		if (idExists(person.getId())) {
			throw new PersonException("Cannot add person. Id already exists.");
		}
		dataSource.put(person.getId(), person);

	}

	public void deletePerson(Integer id) throws PersonException {
		if (!idExists(id)) {
			throw new PersonException("Cannot delete person. Id does not exists.");
		}
		dataSource.remove(id);
	}

	public List<Person> getAllPersons() {
		return new ArrayList<Person>(dataSource.values());
	}

	public Person getPerson(Integer id) throws PersonException {
		if (!idExists(id)) {
			throw new PersonException("Cannot get person. ID does not exist.");
		}
		return dataSource.get(id);
	}

	public void updatePerson(Person person) throws PersonException {
		if (!idExists(person.getId())) {
			throw new PersonException("Cannot update person. ID does not exist.");
		}
		dataSource.put(person.getId(), person);
	}

	@PostConstruct
	public void initialize() {
		dataSource = new HashMap<Integer, Person>();
		putPerson(1, "Clark", "Kent");
		putPerson(2, "Bruce", "Wayne");
		putPerson(3, "Harold", "Jordan");
	}

	private void putPerson(Integer id, String firstName, String lastName) {
		Person person = new Person();
		person.setId(id);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		dataSource.put(id, person);
	}

	private boolean idExists(Integer id) {
		return dataSource.containsKey(id);
	}
}
