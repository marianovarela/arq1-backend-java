package ar.edu.unq.tac.market.domain;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Event extends AbstractEntity {

	
    private String name;
    
    private String place;
    
    private Date created = new Date();
    
    private Date date;
    
    private long userId;
}