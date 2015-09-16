package ar.edu.unq.tac.market.common;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Generator {   
    
    private Random random = new Random();
    
    public String numericCode(int dijits) {        
        return String.valueOf(random.nextInt((int) Math.pow(10, dijits + 1)));
    }
    
}
