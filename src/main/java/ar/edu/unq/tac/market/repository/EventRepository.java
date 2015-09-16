package ar.edu.unq.tac.market.repository;

import org.springframework.data.repository.NoRepositoryBean;

import ar.edu.unq.tac.market.domain.Event;
import ar.edu.unq.tac.market.repository.rest.AbstractRestRepository;

@NoRepositoryBean
public interface EventRepository extends AbstractRestRepository<Event, Long> {

}
