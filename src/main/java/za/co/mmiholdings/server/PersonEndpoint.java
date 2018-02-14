package za.co.mmiholdings.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import za.co.mmiholdings.schema.AddPersonRequest;
import za.co.mmiholdings.schema.DeletePersonRequest;
import za.co.mmiholdings.schema.GetAllPersonsRequest;
import za.co.mmiholdings.schema.GetAllPersonsResponse;
import za.co.mmiholdings.schema.GetPersonRequest;
import za.co.mmiholdings.schema.GetPersonResponse;
import za.co.mmiholdings.schema.Person;
import za.co.mmiholdings.schema.UpdatePersonRequest;
import za.co.mmiholdings.service.PersonException;
import za.co.mmiholdings.service.PersonService;

@Endpoint
public class PersonEndpoint {

	@Autowired
	private PersonService personService;
	
	public static final String NAMESPACE_URI = "http://www.co.za/mmiholdings/schema";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
	@ResponsePayload
	public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) throws PersonException {
		GetPersonResponse response = new GetPersonResponse();
		Person person = personService.getPerson(request.getId());
		response.setPerson(person);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonRequest")
	//@ResponsePayload
	public void updatePerson(@RequestPayload UpdatePersonRequest request) throws PersonException {
		personService.updatePerson(request.getPerson());
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonRequest")
	//@ResponsePayload
	public void addPerson(@RequestPayload AddPersonRequest request) throws PersonException {
		personService.addPerson(request.getPerson());
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
	//@ResponsePayload
	public void deletePerson(@RequestPayload DeletePersonRequest request) throws PersonException {
		personService.deletePerson(request.getId());
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPersonsRequest")
	@ResponsePayload
	public GetAllPersonsResponse updatePerson(@RequestPayload GetAllPersonsRequest request) {
		
		GetAllPersonsResponse response = new GetAllPersonsResponse();
		response.getPerson().addAll(personService.getAllPersons());
		return response;
	}

}
