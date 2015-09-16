package ar.edu.unq.tac.market.common;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class Formatter {
    
    public String blindMail(String mail){
        int indexOfAt = mail.indexOf('@');
        String fromAt = mail.substring(indexOfAt, mail.length());
        if(indexOfAt <= 6){
            String asterisks = asterisks(3);
            if(indexOfAt > 3){
                return mail.substring(0, indexOfAt - 3) + asterisks + fromAt;
            }
            else{
                return asterisks + fromAt; 
            }
        }
        String asterisks = asterisks(indexOfAt - 3);
        return mail.substring(0, 3) + asterisks + fromAt;
    }
    
    public String asterisks(int amount){
        char[] repeat = new char[amount];
        Arrays.fill(repeat, '*');
        return new String(repeat);
    }
  
}
