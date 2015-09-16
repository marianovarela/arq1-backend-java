package ar.edu.unq.tac.market.repository.rest;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.edu.unq.tac.market.repository.EventRepository;

@RepositoryRestResource(path = "event", collectionResourceRel = "event")
public interface EventRestRepository extends EventRepository {
	
}
