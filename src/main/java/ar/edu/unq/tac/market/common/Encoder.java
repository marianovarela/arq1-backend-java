package ar.edu.unq.tac.market.common;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class Encoder {
    
    public String decodeBase64(String encoded){
        byte[] decodeBase64 = Base64.decodeBase64(encoded);
        return new String(decodeBase64);
    }

}
