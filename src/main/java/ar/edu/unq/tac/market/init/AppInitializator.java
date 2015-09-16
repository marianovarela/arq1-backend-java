package ar.edu.unq.tac.market.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AppInitializator {
    
    public static boolean CREATE = true;

    @Autowired
    private InitDatabase applicationDataInit;

    @PostConstruct
    public void init() {
        if(CREATE){
            applicationDataInit.createData();
        }
    }

}
