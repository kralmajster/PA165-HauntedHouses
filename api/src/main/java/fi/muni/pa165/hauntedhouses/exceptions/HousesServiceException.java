package fi.muni.pa165.hauntedhouses.exceptions;

/**
 * The exception thrown by the Service Layer.
 * 
 * @author Klara Kufova, 410091
 */

public class HousesServiceException extends RuntimeException {

    public HousesServiceException() {
        super();
    }
    
    public HousesServiceException(String message) {
        super(message);
    }
    
    public HousesServiceException(Throwable cause) {
        super(cause);
    }
    
    public HousesServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public HousesServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
