package ar.edu.unq.tac.market.init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.unq.tac.market.domain.Event;
import ar.edu.unq.tac.market.repository.EventRepository;
import ar.edu.unq.tac.market.repository.factory.EventFactory;

@Component
public class InitDatabase {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventFactory events;
	
    public void createData(){
    	events.create();
    	
    	List<Event> eventList = events.getScoreAsList();
    	
    	eventRepository.save(eventList);
    }

}
