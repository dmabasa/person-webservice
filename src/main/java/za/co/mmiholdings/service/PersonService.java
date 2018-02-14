package za.co.mmiholdings.service;

import java.util.List;

import za.co.mmiholdings.schema.Person;

public interface PersonService {

	void addPerson(Person person) throws PersonException;
	Person getPerson(Integer id) throws PersonException;
	List<Person> getAllPersons();
	void updatePerson(Person person) throws PersonException;
	void deletePerson(Integer id) throws PersonException;
	
}
