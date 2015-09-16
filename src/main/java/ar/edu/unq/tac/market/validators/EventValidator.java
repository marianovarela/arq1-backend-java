package ar.edu.unq.tac.market.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import ar.edu.unq.tac.market.repository.EventRepository;
import ar.edu.unq.tac.market.service.EventService;

@RepositoryEventHandler
public class EventValidator {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private EventRepository eventRepository;
	
}
