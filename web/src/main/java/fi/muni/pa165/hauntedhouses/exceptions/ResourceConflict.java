package fi.muni.pa165.hauntedhouses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Klara Kufova, 410091
 */

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The resource already exists.")
public class ResourceConflict extends RuntimeException {

}
