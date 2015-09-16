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
	    
	    public String schema[] = {"id", "score", "game", "name"};
	    
	    private String scores_array[] = {
	        /** | id  | tacId  | game | name   | */
	        /** | int | string | Game | string | */

	        " 1, 5, HEXTRIS, Player",
	        " 2, 25, HEXTRIS, Player",
	        " 3, 100, HEXTRIS, Player",
	        " 4, 5000, CHAIN_REACTION, Player",
	        " 5, 10000, CHAIN_REACTION, Player",
	        " 6, 15000, CHAIN_REACTION, Player",
	        " 7, 20000, CHAIN_REACTION, Player",
	        " 8, 25000, CHAIN_REACTION, Player",
	        " 9, 30000, CHAIN_REACTION, Player",
	        " 10, 35000, CHAIN_REACTION, Player",
	        " 11, 40000, CHAIN_REACTION, Player",
	        " 12, 45000, CHAIN_REACTION, Player",
	        " 13, 50000, CHAIN_REACTION, Player"};
	    
	    protected String[] getSchema() {
	        return schema;
	    }
	    
	    public EventFactory create() {
	        events = new TreeMap<Integer, Event>();
	        for (int i = 0; i < scores_array.length; i++) {
	            String splittedScore[] = splitArray(scores_array[i]);
	            Integer constructionId = Integer.valueOf(findValue(splittedScore, "id"));
	            Event event = fromString(splittedScore);
	            events.put(constructionId, event);
	        }
	        return this;
	    }

	    private Event fromString(String[] splittedScore) {
	    	Event event = new Event();
	    	Integer points = Integer.valueOf(findValue(splittedScore, "score"));
	        String name = String.valueOf(findValue(splittedScore, "name"));
	        event.setName(name);
	        return event;
	    }
	    
	    public List<Event> getScoreAsList() {
	        List<Event> scoreList = new LinkedList<Event>();
	        scoreList.addAll(events.values());
	        return scoreList;
	    }

	    public Map<Integer, Event> getTacConfig() {
	        return events;
	    }
		
}
