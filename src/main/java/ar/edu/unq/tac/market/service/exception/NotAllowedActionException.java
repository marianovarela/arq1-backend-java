package ar.edu.unq.tac.market.service.exception;

public class NotAllowedActionException extends MarketRuntimeException {

    private static final long serialVersionUID = 1L;
    
    public NotAllowedActionException() {
        super("NotAllowedAction", "Not allowed");
    }

}
