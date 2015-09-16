package ar.edu.unq.tac.market.service.exception;

public class MarketRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    private String keyError;
    private String jsonParams;
    
    public MarketRuntimeException(final String keyError, final String message) {
        super(message);
        this.keyError = keyError;
        this.jsonParams = null;
    }
    
    public MarketRuntimeException(final String keyError, final String message, final String jsonParams) {
        this(keyError, message);
        this.jsonParams = jsonParams;
    }

    public String getKeyError() {
        return keyError;
    }
    
    public String getJsonParams(){
        return jsonParams;
    }

    
}
