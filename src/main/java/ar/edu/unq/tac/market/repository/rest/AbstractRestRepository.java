package ar.edu.unq.tac.market.repository.rest;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRestRepository<T, ID extends Serializable>
    extends JpaRepository<T, ID>{
    
}
