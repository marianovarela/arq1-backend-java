package ar.edu.unq.tac.market.repository.factory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import ar.edu.unq.tac.market.domain.Event;

@Component
public class EventFactory extends EntityFactory{

	 private Map<Integer, Event> events;
	    
	    public String schema[] = {"id", "name", "place"};
	    
	    private String events_array[] = {
	        /** | id  | tacId  | game | name   | */
	        /** | int | string | Game | string | */

	        " 1, Juntada, Quilmes",
	        " 2, Reunion, Capital",
	        " 3, asado, El tano",
	        " 4, pizza, Los tres ases",
	        " 5, cumpleaños, mi casa",
	        " 6, nos juntamos, en algun lado"};
	    
	    protected String[] getSchema() {
	        return schema;
	    }
	    
	    public EventFactory create() {
	        events = new TreeMap<Integer, Event>();
	        for (int i = 0; i < events_array.length; i++) {
	            String splittedEvent[] = splitArray(events_array[i]);
	            Integer constructionId = Integer.valueOf(findValue(splittedEvent, "id"));
	            Event event = fromString(splittedEvent);
	            events.put(constructionId, event);
	        }
	        return this;
	    }

	    private Event fromString(String[] splittedScore) {
	    	Event event = new Event();
	        String name = String.valueOf(findValue(splittedScore, "name"));
	        event.setName(name);
	        String place = String.valueOf(findValue(splittedScore, "place"));
	        event.setPlace(place);
	        return event;
	    }
	    
	    public List<Event> getEventAsList() {
	        List<Event> eventList = new LinkedList<Event>();
	        eventList.addAll(events.values());
	        return eventList;
	    }

	    public Map<Integer, Event> getTacConfig() {
	        return events;
	    }
		
}
